package com.rfgvieira.auau.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val state = remember {
            DogFormState(dogViewModel = dogViewModel)
        }

        DogForm(showCamera = showCamera, state = state)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 16.dp),
            onClick = {
                addDog(navigateBackToList, state, dogViewModel)
            }, enabled = state.birthdayValid && state.nameValid
        ) {
            Text(text = "Add", fontSize = 24.sp)
        }
    }
}

fun addDog(
    navigateBackToList: () -> Unit,
    state: DogFormState,
    dogViewModel: DogViewModel
) {
    val newDog =
        Dog(
            name = state.name,
            birth = state.birthday,
            favoriteFood = state.food,
            imgUri = state.img.value,
            hobbies = state.hobbies
        )
    DogDAO.add(newDog)
    dogViewModel.emptyUri()
    navigateBackToList()
}






