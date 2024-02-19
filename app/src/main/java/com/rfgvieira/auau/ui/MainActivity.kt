package com.rfgvieira.auau.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.screens.CameraView
import com.rfgvieira.auau.ui.screens.DogAddScreen
import com.rfgvieira.auau.ui.screens.DogEditScreen
import com.rfgvieira.auau.ui.screens.DogListScreen
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
                    NavHost(navController = navController, startDestination = "doglist", enterTransition = { EnterTransition.None}, exitTransition = { ExitTransition.None}) {
                        composable("doglist") {
                            showFAB.value = true
                            showTopBar.value = true
                            DogListScreen(dogViewModel){ dog -> navController.navigate("dogdetails/${dog.id}") }
                        }
                        composable("dogadd") {
                            showTopBar.value = true
                            showFAB.value = false
                            DogAddScreen(showCamera, dogViewModel){
                                navController.popBackStack("doglist", inclusive = false)
                            }
                        }
                        composable("dogdetails/{dogId}"){backStack ->
                            showFAB.value = false
                            showTopBar.value = false

                            val id = backStack.arguments?.getString("dogId")
                            Dogs.dogsList().find {
                                it.id == id
                            }?.let{dog ->
                                DogEditScreen(dog, dogViewModel) {
                                    navController.popBackStack("doglist", false)
                                }
                            }

                        }
                        composable("camera") {
                            showTopBar.value = false
                            CameraView(
                                outputDirectory = outPutDirectory,
                                executor = cameraExecutor,
                                onImageCaptured = ::handleImageCapture,
                                onError = { Log.i("Camera error", "Error: $it") },
                                showCamera
                            ){
                                navController.popBackStack("dogadd", false)
                            }
                        }
                    }
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
        topBar = { if (showTopBar) TopBar(modifier = Modifier) },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.paw_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.icon_size))
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )

                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    )
}

