package com.rfgvieira.auau.presentation.screens


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog
import com.rfgvieira.auau.presentation.components.DogCard
import com.rfgvieira.auau.presentation.components.SearchBar
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

//Tela para listar todos os cachorros cadastrados

@Composable
fun DogListScreen(viewModel: DogViewModel, navigateToDetails: (Dog) -> Unit) {
    var dogList = viewModel.dogList
    if (dogList.isEmpty()) {
        dogList.addAll(DogDAO.dogsList())
    }

    var searchText by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(4.dp))

        SearchBar(modifier = Modifier.fillMaxWidth(0.7f), onSearch = {
            searchText = it

        }, text = searchText)


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
            dogList = dogItemFilter(searchText, dogList)
            
            item{
                if(dogList.isEmpty()){
                    Text(text = "Don't exists dogs with the searched name", fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp))
                }
            }
            
            items(
                items = dogList
            ) { dog ->


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

}

fun dogItemFilter(searchText: String, dogList: SnapshotStateList<Dog>,): SnapshotStateList<Dog> {
    return if(searchText.isNotEmpty()){
        dogList.filter{dog -> dog.name.contains(searchText, true)}.toMutableStateList()
    } else
        dogList
}

@Composable
@Preview(showBackground = true)
fun DogListPreview() {
    DogListScreen(viewModel(), {})
}