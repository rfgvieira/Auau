package com.rfgvieira.auau.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getString
import com.rfgvieira.auau.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
class CameraUtils {
    companion object {
        private lateinit var permissionLauncher: ManagedActivityResultLauncher<String, Boolean>

        @Composable
        fun IntializeLauncher(context: Context, state: MutableState<Boolean>) {

            permissionLauncher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
                    if (it)
                        state.value = true
                    else
                        Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }

        }

        @Composable
        fun GetImageFromCamera(context: Context, state: MutableState<Boolean>) {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                state.value = true
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }

        }

        fun takePhoto(
            imageCapture: ImageCapture,
            outputDirectory: File,
            executor: Executor,
            onImageCaptured: (Uri) -> Unit,
            onError: (ImageCaptureException) -> Unit
        ) {
            val timeStamp = SimpleDateFormat(
                "ddMMyyyy_HHmmss",
                Locale("pt-br")
            ).format(System.currentTimeMillis())

            val photoFile = File(
                outputDirectory,
                "$timeStamp.jpg"
            )

            val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture.takePicture(outputOption, executor, object :
                ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val uri = Uri.fromFile(photoFile)
                    onImageCaptured(uri)
                }

                override fun onError(exception: ImageCaptureException) {
                    onError(exception)
                }

            })
        }

        fun getOutputDirectory(context: Context) : File{
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let{
                File(it, getString(context, R.string.app_name)).apply { mkdirs() }
            }
            return if(mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
        }

        suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { it ->
            ProcessCameraProvider.getInstance(this).also{ provider ->
                provider.addListener({
                    it.resume(provider.get())

                }, ContextCompat.getMainExecutor(this))

            }
        }
    }

}

class EnumUtils {

    enum class KeyboardOptions {
        DONE,
        NEXT
    }

}