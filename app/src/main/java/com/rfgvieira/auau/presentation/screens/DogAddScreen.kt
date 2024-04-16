package com.rfgvieira.auau.presentation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.components.DogForm
import com.rfgvieira.auau.presentation.components.DogFormState
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

//Tela para adicionar um novo cachorro ao app

/*TODO: Persistencia dos dados ao ir para camera*/
@Composable
fun DogAddScreen(
    showCamera: MutableState<Boolean>,
    dogViewModel: DogViewModel,
    navigateBackToList: () -> Unit
) {

    val img = dogViewModel.uriImage.observeAsState(Uri.EMPTY)




    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val state = remember {
            DogFormState()
        }
        DogForm(showCamera = showCamera, dogViewModel = dogViewModel, state = state)
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 16.dp),
            onClick = {
                addDog(
                    navigateBackToList,
                    state.name,
                    state.birthday,
                    state.food,
                    img.value,
                    state.hobbies.toList()
                )
            }, enabled = state.birthdayValid && state.nameValid
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
    DogDAO.save(newDog)
    navigateBackToList()
}






