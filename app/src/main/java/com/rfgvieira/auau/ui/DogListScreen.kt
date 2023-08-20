package com.rfgvieira.auau.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.theme.AuauTheme

@Composable
fun DogListScreen(modifier: Modifier) {
    AuauTheme {
        Column(modifier) {
            DogList()
        }
    }
}


@Composable
fun DogList() {
    val dogList: List<Dog> = Dogs.dogsList()
    LazyColumn(
        Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogCard(modifier: Modifier, dog: Dog) {
    Card(modifier = modifier) {
        Row(Modifier.padding(dimensionResource(id = R.dimen.padding_mediumsmall))) {
            Image(
                painter = painterResource(id = dog.img),
                contentDescription = "pug",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(dimensionResource(id = R.dimen.icon_size))
            )
            Column(Modifier.padding(start = 16.dp)) {
                Text(text = dog.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = dog.birth, fontSize = 12.sp)
            }
        }
    }
}


@Composable
@Preview
fun DogListPreview() {
    DogListScreen(
        Modifier
            .fillMaxSize()
    )
}