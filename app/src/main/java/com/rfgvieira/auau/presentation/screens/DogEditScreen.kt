package com.rfgvieira.auau.presentation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.components.DogForm
import com.rfgvieira.auau.presentation.components.DogFormState
import com.rfgvieira.auau.presentation.components.TopBarDog
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

//Tela para modificar um cachorro já existente
@Composable
fun DogEditScreen(
    dog: Dog,
    showCamera: MutableState<Boolean>,
    dogViewModel: DogViewModel,
    navigateBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopBarDog(
            modifier = Modifier,
            navigateBack = navigateBack,
            title = {
                Text(
                    dog.name, color = Color.White, fontSize = 20.sp,
                )
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        val state = remember {
            with(dog) {
                dogViewModel.changeUri(uri = imgUri ?: Uri.EMPTY)
                DogFormState(
                    name = name,
                    birthday = birth,
                    food = favoriteFood ?: "",
                    hobbies = hobbies,
                    dogViewModel
                )
            }
        }

        DogForm(showCamera = showCamera, state = state)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 16.dp),
            onClick = {
                editDog(dog, state)
                dogViewModel.emptyUri()
                navigateBack()
            }, enabled = state.birthdayValid && state.nameValid
        ) {
            Text(text = "Edit", fontSize = 24.sp)
        }
    }
}

fun editDog(
    dog: Dog,
    state : DogFormState
) {
    dog.name = state.name
    dog.birth = state.birthday
    dog.favoriteFood = state.food
    dog.imgUri = state.img.value
    dog.hobbies = state.hobbies
}


@Composable
@Preview(showBackground = true)
fun DogEditPreview() {
    val state = remember { mutableStateOf(false) }
    DogEditScreen(DogDAO.dogsList()[0], state, DogViewModel(), {})
}