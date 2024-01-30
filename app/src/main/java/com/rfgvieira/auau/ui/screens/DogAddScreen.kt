package com.rfgvieira.auau.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DateInput
import com.rfgvieira.auau.ui.components.TextInput
import com.rfgvieira.auau.ui.viewmodel.DogViewModel
import com.rfgvieira.auau.utils.CameraUtils.Companion.GetImageFromCamera
import com.rfgvieira.auau.utils.EnumUtils

@Composable
fun DogAddScreen(navController: NavHostController,showCamera : MutableState<Boolean>, dogViewModel: DogViewModel) {
    val name = remember { mutableStateOf("") }
    val birthday = remember { mutableStateOf("") }
    val food = remember { mutableStateOf("") }
    var clickImage by remember { mutableStateOf(false) }
    val img = dogViewModel.uriImage.observeAsState(Uri.EMPTY)

    if (clickImage) {
        GetImageFromCamera(LocalContext.current, showCamera)
        clickImage = false
    }



    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clickable {
                    clickImage = true
                }
        ) {
            if (img.value == Uri.EMPTY) {
                Row(
                    Modifier
                        .background(Color.LightGray)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Click to upload image")
                }

            } else {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = img.value,
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            }
        }

        val focusManager = LocalFocusManager.current

        TextInput(
            focusManager = focusManager, name, "Name",
            EnumUtils.KeyboardOptions.NEXT
        )


        DateInput(state = birthday, focusManager = focusManager, placeholder = "Birthday")


        TextInput(
            focusManager = focusManager, state = food, placeholder = "Favorite Food",
            option = EnumUtils.KeyboardOptions.DONE
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 16.dp),
            onClick = { addDog(navController, name.value, birthday.value, food.value,img.value) }) {
            Text(text = "Add", fontSize = 24.sp)
        }
    }
}

fun addDog(navController: NavHostController, name: String, birthday: String, food: String, img: Uri) {
    val newDog = Dog(name = name, birth = birthday, favoriteFood = food, imgUri = img)
    Dogs.addNewDog(newDog)
    navController.popBackStack("doglist", inclusive = false)
}






