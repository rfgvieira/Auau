package com.rfgvieira.auau


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DogListScreen() {
    Column(Modifier.fillMaxSize()) {
        
    }

}


@Composable
fun DogList() {
    LazyColumn(){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogCard(modifier: Modifier) {
    Card(modifier = modifier) {
        Row(Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = R.drawable.pug),
                contentDescription = "pug",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
            )
            Column(Modifier.padding(start = 8.dp)) {
                Text(text = "Poe", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "15/6/2021", fontSize = 12.sp)
            }
        }
    }
}


@Composable
@Preview
fun DogListPreview(){
    DogCard(Modifier)
}