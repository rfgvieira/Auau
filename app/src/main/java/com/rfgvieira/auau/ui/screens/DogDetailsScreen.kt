package com.rfgvieira.auau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DogImage
import com.rfgvieira.auau.ui.components.TopBarDog

@Composable
fun DogEditScreen(dog: Dog, navigateBack : () -> Unit) {
    Column {
       TopBarDog(modifier = Modifier
           .background(MaterialTheme.colorScheme.primaryContainer)
           .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
       ) { navigateBack() }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DogImage(
                dog = dog, modifier = Modifier
                    .clip(CircleShape)
                    .size(dimensionResource(id = R.dimen.image_size))
                    .border(width = 1.dp, Color.Black, CircleShape)
            )

            Text(text = dog.name, fontSize = 24.sp, fontWeight = FontWeight.W700)
            Column(modifier = Modifier.fillMaxWidth(0.8f).padding(top = 32.dp)) {
                Text(text = "Birthdate", fontSize = 20.sp, fontWeight = FontWeight.W500)
                Text(text = dog.birth, fontSize = 16.sp)
                Column (modifier = Modifier.padding(top = 24.dp)){
                    Text(text = "Favorite Food", fontSize = 20.sp, fontWeight = FontWeight.W500)
                    Text(text = dog.favoriteFood, fontSize = 16.sp)
                }

            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun DogEditPreview() {
    DogEditScreen(Dogs.dogsList()[0], {})
}