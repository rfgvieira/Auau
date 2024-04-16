package com.rfgvieira.auau.presentation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.components.DogForm
import com.rfgvieira.auau.presentation.components.TopBarDog
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

//Tela para modificar um cachorro já existente
@Composable
fun DogEditScreen(dog: Dog, showCamera: MutableState<Boolean>, dogViewModel: DogViewModel, navigateBack: () -> Unit) {


    TopBarDog(
        modifier = Modifier,
        navigateBack = navigateBack,
        title = {
            Text(
                dog.name, color = Color.White, fontSize = 20.sp,
            )
        }
    )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        val img = dogViewModel.uriImage.observeAsState(Uri.EMPTY)

        DogForm(showCamera = showCamera, dogViewModel = dogViewModel)
    }
}


@Composable
@Preview(showBackground = true)
fun DogEditPreview() {
    val state = remember{ mutableStateOf(false) }
    DogEditScreen(DogDAO.dogsList()[0], state, DogViewModel(), {})
}