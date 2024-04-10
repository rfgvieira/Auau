package com.rfgvieira.auau.presentation.components

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel
import com.rfgvieira.auau.utils.CameraUtils
import com.rfgvieira.auau.utils.EnumUtils

@Composable
fun DogForm(
    showCamera: MutableState<Boolean>,
    dogViewModel: DogViewModel,
    dog : Dog? = null
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

    val context = LocalContext.current
    val hobbiesList = listOf("Dig", "Walk", "Bark", "Play", "Sleep", "Pat")

    if (clickImage) {
        CameraUtils.GetImageFromCamera(LocalContext.current, showCamera)
        clickImage = false
    }




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
    ) { input ->
        name.value = input
        nameValid.value = input.isNotEmpty()
    }


    DateInput(
        state = birthday,
        isValid = birthdayValid,
        focusManager = focusManager,
        placeholder = "Birthday"
    ) {
        birthday.value = it
    }


    TextInput(
        focusManager = focusManager, state = food, placeholder = "Favorite Food",
        option = EnumUtils.KeyboardOptions.DONE,
        modifier = Modifier.padding(vertical = 16.dp)
    ) { food.value = it }

    MultipleSelect("Hobbies", hobbiesList, Modifier) { item ->
        if (!hobbies.contains(item))
            hobbies.add(item)
        else
            Toast.makeText(context, "Hobby already added", Toast.LENGTH_SHORT).show()
    }

    DisplayTagsEdit(hobbies, Modifier.padding(bottom = 12.dp))



    if (!(birthdayValid.value && nameValid.value)) {
        Text(
            text = "Required fields empty",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.offset(y = dimensionResource(id = R.dimen.padding_medium))
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DogFormPreview() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        DogForm(showCamera = remember {
            mutableStateOf(false)
        }, dogViewModel = DogViewModel())
    }
}
