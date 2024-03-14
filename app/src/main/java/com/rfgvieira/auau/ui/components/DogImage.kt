package com.rfgvieira.auau.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.rfgvieira.auau.domain.Dog
/*TODO: Placeholder sem foto*/

@Composable
fun DogImage(dog: Dog, modifier: Modifier) {
    dog.imgDrawable?.let {
        Image(
            painter = painterResource(id = it),
            contentDescription = "dogImage",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }

    dog.imgUri?.let {
        AsyncImage(
            model = it, contentDescription = "dogImage",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }

}
