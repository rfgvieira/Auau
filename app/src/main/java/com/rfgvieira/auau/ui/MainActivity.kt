package com.rfgvieira.auau.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.rfgvieira.auau.ui.components.TopBarApp
import com.rfgvieira.auau.ui.navigation.NavigationApp
import com.rfgvieira.auau.ui.theme.AuauTheme
import com.rfgvieira.auau.ui.viewmodel.DogViewModel
import com.rfgvieira.auau.utils.CameraUtils
import com.rfgvieira.auau.utils.CameraUtils.Companion.getOutputDirectory
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {

    private lateinit var outPutDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var showCamera: MutableState<Boolean> = mutableStateOf(false)
    private val dogViewModel by viewModels<DogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()
            val showFAB = remember { mutableStateOf(true) }
            val showTopBar = remember { mutableStateOf(true) }
            val isDark = false
            var hasLaunched by remember { mutableStateOf(false) }

            if (!hasLaunched) {
                CameraUtils.IntializeLauncher(context = LocalContext.current, showCamera)
                hasLaunched = true
            }

            if (showCamera.value) {
                navController.navigate("camera")
            }

            AuauTheme(darkTheme = isDark) {
                AuauApp(
                    showFab = showFAB.value,
                    showTopBar = showTopBar.value,
                    onFabClick = { navController.navigate("dogadd") }) {
                    NavigationApp(
                        navController = navController,
                        dogViewModel = dogViewModel,
                        showCamera = showCamera,
                        cameraExecutor = cameraExecutor,
                        outPutDirectory = outPutDirectory,
                        handleImageCapture = ::handleImageCapture,
                        onNavigateToList = {
                            showFAB.value = true
                            showTopBar.value = true
                        },
                        onNavigateToAdd = {
                            showTopBar.value = true
                            showFAB.value = false
                        },
                        onNavigateToCamera = {
                            showTopBar.value = false
                        },
                        onNavigateToDetails = {
                            showFAB.value = false
                            showTopBar.value = false
                        })
                }
            }
            outPutDirectory = getOutputDirectory(LocalContext.current)
            cameraExecutor = Executors.newSingleThreadExecutor()
        }
    }

    private fun handleImageCapture(uri: Uri) {
        dogViewModel.changeUri(uri)
        showCamera.value = false
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}

@Composable
fun AuauApp(
    showFab: Boolean,
    showTopBar: Boolean,
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = { if (showTopBar) TopBarApp(modifier = Modifier) },
        floatingActionButton = {
            if (showFab) {
                FloatingActionButton(
                    onClick = onFabClick,
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text(text = "+")
                }
            }

        }

    ) {
        Box(modifier = Modifier.padding(it)) {
            content()
        }
    }
}




