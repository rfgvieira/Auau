package com.rfgvieira.auau.ui.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.screens.CameraView
import com.rfgvieira.auau.ui.screens.DogAddScreen
import com.rfgvieira.auau.ui.screens.DogEditScreen
import com.rfgvieira.auau.ui.screens.DogListScreen
import com.rfgvieira.auau.ui.viewmodel.DogViewModel
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun NavigationApp(
    navController: NavHostController,
    dogViewModel: DogViewModel,
    showCamera : MutableState<Boolean>,
    cameraExecutor : ExecutorService,
    handleImageCapture : (Uri) -> Unit,
    outPutDirectory : File,
    onNavigateToList: () -> Unit,
    onNavigateToAdd: () -> Unit,
    onNavigateToDetails: () -> Unit,
    onNavigateToCamera: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "doglist",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("doglist") {
            onNavigateToList()
            DogListScreen(dogViewModel) { dog -> navController.navigate("dogdetails/${dog.id}") }
        }
        composable("dogadd") {
            onNavigateToAdd()
            DogAddScreen(showCamera, dogViewModel) {
                navController.popBackStack("doglist", inclusive = false)
            }
        }
        composable("dogdetails/{dogId}") { backStack ->
            onNavigateToDetails()

            val id = backStack.arguments?.getString("dogId")
            Dogs.dogsList().find {
                it.id == id
            }?.let { dog ->
                DogEditScreen(dog, dogViewModel) {
                    navController.popBackStack("doglist", false)
                }
            }

        }
        composable("camera") {
            onNavigateToCamera()
            CameraView(
                outputDirectory = outPutDirectory,
                executor = cameraExecutor,
                onImageCaptured = handleImageCapture,
                onError = { Log.i("Camera error", "Error: $it") },
                showCamera
            ) {
                navController.popBackStack("dogadd", false)
            }
        }
    }
}