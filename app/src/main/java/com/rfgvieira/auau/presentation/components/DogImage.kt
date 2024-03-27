package com.rfgvieira.auau.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.model.Dog

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
        if(!Uri.EMPTY.equals(it)){
            AsyncImage(
                model = it, contentDescription = "dogImage",
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.dog_placeholder),
                contentDescription = "dogPlaceholder",
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
        }
    }
}
