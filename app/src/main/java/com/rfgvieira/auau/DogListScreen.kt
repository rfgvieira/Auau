package com.rfgvieira.auau


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.ui.theme.AuauTheme

@Composable
fun DogListScreen(modifier : Modifier) {
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
            .padding(horizontal = 16.dp)
    ) {
        items(dogList) { dogs ->
            DogCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                dog = dogs
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogCard(modifier: Modifier, dog: Dog) {
    Card(modifier = modifier) {
        Row(Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = dog.img),
                contentDescription = "pug",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
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
    DogListScreen(Modifier
        .fillMaxSize()
        .background(Color.White))
}