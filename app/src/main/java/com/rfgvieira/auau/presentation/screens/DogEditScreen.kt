package com.rfgvieira.auau.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rfgvieira.auau.presentation.components.TopBarDog
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

@Composable
fun DogEditScreen(dogViewModel: DogViewModel, navigateBack : () -> Unit) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TopBarDog(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            navigateBack = navigateBack,
            title = {

            }
        )
        Text("Ez")
    }
}

@Composable
@Preview(showBackground = true)
fun DogEditPreview(){
    DogEditScreen(DogViewModel(),{})
}