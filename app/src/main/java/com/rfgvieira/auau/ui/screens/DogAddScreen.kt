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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DateInput
import com.rfgvieira.auau.ui.components.Select
import com.rfgvieira.auau.ui.components.TextInput
import com.rfgvieira.auau.ui.viewmodel.DogViewModel
import com.rfgvieira.auau.utils.CameraUtils.Companion.GetImageFromCamera
import com.rfgvieira.auau.utils.EnumUtils

//Tela para adicionar um novo cachorro ao app

/*TODO: Persistencia dos dados ao ir para camera*/
@Composable
fun DogAddScreen(
    showCamera: MutableState<Boolean>,
    dogViewModel: DogViewModel,
    navigateBackToList: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val nameValid = remember { mutableStateOf(false) }
    val birthday = remember { mutableStateOf("") }
    val birthdayValid = remember { mutableStateOf(false) }
    val food = remember { mutableStateOf("") }
    var clickImage by remember { mutableStateOf(false) }
    val img = dogViewModel.uriImage.observeAsState(Uri.EMPTY)
    val hobbies = remember {
        mutableStateListOf<String>()
    }
    val hobbiesList = listOf("Dig","Walk","Bark","Play","Sleep","Pat")

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
            focusManager = focusManager, name, nameValid, "Name",
            EnumUtils.KeyboardOptions.NEXT,
            modifier = Modifier.padding(vertical = 16.dp)
        )


        DateInput(
            state = birthday,
            isValid = birthdayValid,
            focusManager = focusManager,
            placeholder = "Birthday"
        )


        TextInput(
            focusManager = focusManager, state = food, placeholder = "Favorite Food",
            option = EnumUtils.KeyboardOptions.DONE,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Select(hobbies, hobbiesList, Modifier)



        if (!(birthdayValid.value && nameValid.value)) {
            Text(
                text = "Required fields empty",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.offset(x = dimensionResource(id = R.dimen.padding_medium))
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 16.dp),
            onClick = {
                addDog(
                    navigateBackToList,
                    name.value,
                    birthday.value,
                    food.value,
                    img.value,
                    hobbies.toList()
                )
            }, enabled = birthdayValid.value && nameValid.value
        ) {
            Text(text = "Add", fontSize = 24.sp)
        }
    }
}

fun addDog(
    navigateBackToList: () -> Unit,
    name: String,
    birthday: String,
    food: String,
    img: Uri,
    hobbies: List<String>
) {
    val newDog =
        Dog(name = name, birth = birthday, favoriteFood = food, imgUri = img, hobbies = hobbies)
    Dogs.addNewDog(newDog)
    navigateBackToList()
}






