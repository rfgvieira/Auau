package com.rfgvieira.auau.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DogCard


@Composable
fun DogListScreen() {
    DogList()
}

@Composable
fun DogList() {
    val dogList: List<Dog> = Dogs.dogsList()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(dogList) { dogs ->
            DogCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_mediumsmall)),
                dog = dogs
            )
        }
    }
}

@Composable
@Preview
fun DogListPreview() {
    DogListScreen()
}