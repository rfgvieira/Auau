package com.rfgvieira.auau.ui.screens


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.components.DogCard
import com.rfgvieira.auau.ui.viewmodel.DogViewModel

//Tela para listar todos os cachorros cadastrados
@Composable
fun DogListScreen(viewModel: DogViewModel, navigateToDetails: (Dog) -> Unit) {
    val dogList = viewModel.dogList
    if (dogList.isEmpty()) {
        dogList.addAll(Dogs.dogsList())
    }


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
        items(viewModel.dogList) { dog ->


            DogCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                dog = dog, navigateToDetails
            ) { deletedDog -> viewModel.deleteDog(deletedDog) }


        }

    }
    Spacer(modifier = Modifier.padding(16.dp))


}

@Composable
@Preview(showBackground = true)
fun DogListPreview() {
    DogListScreen(viewModel(), {})

}