package com.rfgvieira.auau.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DogCard


@Composable
fun DogListScreen(navigateToDetails: (Dog) -> Unit) {
    val dogList: List<Dog> = Dogs.dogsList()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_medium),
                end = dimensionResource(id = R.dimen.padding_medium),
                bottom = dimensionResource(id = R.dimen.padding_small)
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        items(dogList) { dogs ->
            DogCard(
                modifier = Modifier
                    .fillMaxWidth(),
                dog = dogs, navigateToDetails
            )
        }

    }
    Spacer(modifier = Modifier.padding(16.dp))
}

@Composable
@Preview(showBackground = true)
fun DogListPreview() {
    DogListScreen({})

}