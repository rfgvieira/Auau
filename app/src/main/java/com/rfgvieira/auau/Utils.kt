package com.rfgvieira.auau

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Objects


class DateUtils {
    companion object {

        fun Long.toDateFormat(pattern: String = "dd/MM/yyyy") : String{
            val date = Date(this)
            val formatter = SimpleDateFormat(pattern, Locale("pt-br"))
            return formatter.format(date)
        }

        fun String.toYear(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                yearNew(this).toString()
            else
                yearOld(this).toString()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun yearNew(date: String): Int {
            val parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            return Period.between(parsedDate, LocalDate.now()).years
        }

        fun yearOld(date: String): Int {

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt-br"))
            val parsedDate = formatter.parse(date)

            val birth = Calendar.getInstance()
            val currentDate = Calendar.getInstance()

            if (parsedDate != null) {
                birth.time = parsedDate
            }

            val year = birth.get(Calendar.YEAR)
            val month = birth.get(Calendar.MONTH)
            val day = birth.get(Calendar.DAY_OF_MONTH)

            birth.set(year, month + 1, day)

            var age = currentDate.get(Calendar.YEAR) - birth.get(Calendar.YEAR)

            if (currentDate.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR))
                age--

            return age
        }
    }
}

class CameraUtils {
    companion object {
        private var result: Uri = Uri.EMPTY
        private lateinit var cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>
        private lateinit var permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
        private lateinit var uri: Uri

        @Composable
        fun IntializeLauncher(context: Context, result: MutableState<Uri>) {


            val file = context.createImageFile()
            uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                BuildConfig.APPLICATION_ID + ".fileprovider", file
            )

            cameraLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                    result.value = uri
                }


            permissionLauncher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
                    if (it)
                        cameraLauncher.launch(uri)
                    else
                        Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }

        }

        @Composable
        fun GetImageFromCamera(context: Context) {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            }

        }

        fun Context.createImageFile(): File {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",Locale("pt-br")).format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            return File.createTempFile(
                imageFileName,
                ".jpg",
                externalCacheDir
            )
        }
    }

}

class EnumUtils{

        enum class KeyboardOptions{
            DONE,
            NEXT
        }

}