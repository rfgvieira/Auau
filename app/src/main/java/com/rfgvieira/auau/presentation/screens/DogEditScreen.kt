package com.rfgvieira.auau.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.components.TopBarDog
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

//Tela para modificar um cachorro já existente
@Composable
fun DogEditScreen(dog: Dog, dogViewModel: DogViewModel, navigateBack: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopBarDog(
            modifier = Modifier,
            navigateBack = navigateBack,
            title = {
                Text(
                    dog.name, color = Color.White, fontSize = 20.sp,
                )
            }
        )
        Text("Ez")
    }
}


@Composable
@Preview(showBackground = true)
fun DogEditPreview() {
    DogEditScreen(DogDAO.dogsList()[0], DogViewModel(), {})
}