package com.rfgvieira.auau.presentation.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.screens.AboutScreen
import com.rfgvieira.auau.presentation.screens.CameraView
import com.rfgvieira.auau.presentation.screens.DogAddScreen
import com.rfgvieira.auau.presentation.screens.DogDetailsScreen
import com.rfgvieira.auau.presentation.screens.DogEditScreen
import com.rfgvieira.auau.presentation.screens.DogListScreen
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel
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
    enableAll: () -> Unit,
    disableFAB: () -> Unit,
    disableAll: () -> Unit,
    disableTopBar: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = "doglist",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("doglist") {
            enableAll()
            DogListScreen(dogViewModel) { dog -> navController.navigate("dogdetails/${dog.id}") }
        }
        composable("dogadd") {
            disableFAB()
            DogAddScreen(showCamera, dogViewModel) {
                navController.popBackStack("doglist", inclusive = false)
            }
        }
        composable("dogdetails/{dogId}") { backStack ->
            DogInfo(onNavigateToDetails = disableAll, backStack = backStack) { dog ->
                DogDetailsScreen(dog, dogViewModel, navigateToEdit = {
                    navController.navigate("dogedit/${dog.id}")
                }) {
                    navController.popBackStack("doglist", false)
                }
            }
        }
        composable("dogedit/{dogId}"){backStack ->
            DogInfo(onNavigateToDetails = disableAll, backStack = backStack) { dog ->
                DogEditScreen(dog, showCamera, dogViewModel){
                    navController.popBackStack()
                }
            }
        }
        composable("camera") {
            disableTopBar()
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
        composable("about") {
            disableFAB()
            AboutScreen()
        }
    }
}

@Composable
fun DogInfo(onNavigateToDetails: () -> Unit,  backStack: NavBackStackEntry, screen : @Composable ((Dog) -> Unit)){
    onNavigateToDetails()

    val id = backStack.arguments?.getString("dogId")
    DogDAO.dogsList().find {
        it.id == id
    }?.let { dog ->
        screen(dog)
    }
}