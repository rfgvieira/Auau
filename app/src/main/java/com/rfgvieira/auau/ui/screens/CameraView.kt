package com.rfgvieira.auau.ui.screens

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.rfgvieira.auau.utils.CameraUtils.Companion.getCameraProvider
import com.rfgvieira.auau.utils.CameraUtils.Companion.takePhoto
import java.io.File
import java.util.concurrent.Executor

//Tela com a funcionalidade de mostrar camera do celular e tirar uma foto

@Composable
fun CameraView(
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit,
    showCamera: MutableState<Boolean>,
    navigateBackToAdd : () -> Unit
) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }
    val imageCapture = remember {
        ImageCapture.Builder().build()
    }

    val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

    LaunchedEffect(key1 = lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    if(!showCamera.value){
        navigateBackToAdd()
    }

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize().zIndex(1f)) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        IconButton(modifier = Modifier.padding(bottom = 20.dp).size(75.dp), onClick =
        {
            takePhoto(
                imageCapture = imageCapture,
                outputDirectory = outputDirectory,
                executor = executor,
                onImageCaptured = onImageCaptured,
                onError = onError
            )

        }, content = {
            Icon(
                imageVector = Icons.Sharp.Lens,
                contentDescription = "Take Picture",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .border(1.dp, Color.White, CircleShape)
            )
        })
    }

}
