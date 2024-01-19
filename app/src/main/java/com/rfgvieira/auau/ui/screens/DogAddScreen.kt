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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rfgvieira.auau.EnumUtils
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DateInput
import com.rfgvieira.auau.ui.components.TextInput

@Composable
fun DogAddScreen(navController: NavHostController) {
    val name = remember { mutableStateOf("") }
    val birthday = remember { mutableStateOf("") }
    val food = remember { mutableStateOf("") }
    val img = remember { mutableStateOf<Uri>(Uri.EMPTY) }
    var clickImage by remember { mutableStateOf(false) }
    



    if (clickImage) {
        /*TODO*/
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
            onClick = { addDog(navController, name.value, birthday.value, food.value) }) {
            Text(text = "Add", fontSize = 24.sp)
        }
    }
}

fun addDog(navController: NavHostController, name: String, birthday: String, food: String) {
    val newDog = Dog(name = name, birth = birthday, favoriteFood = food, img = R.drawable.dog1)
    Dogs.addNewDog(newDog)
    navController.popBackStack("doglist", inclusive = false)


}




