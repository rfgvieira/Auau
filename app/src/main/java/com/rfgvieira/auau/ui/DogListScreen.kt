package com.rfgvieira.auau.ui


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.DateUtils.Companion.toYear
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs
import com.rfgvieira.auau.ui.theme.AuauTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogListScreen(isDark: Boolean = false) {
    AuauTheme(darkTheme = isDark) {
        Scaffold(topBar = { TopBar(modifier = Modifier) }) {
            DogList(it)
        }
    }
}

@Composable
fun TopBar(modifier: Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.paw_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.icon_size))
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )

                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    )
}


@Composable
fun DogList(paddingValues: PaddingValues) {
    val dogList: List<Dog> = Dogs.dogsList()
    LazyColumn(
        contentPadding = paddingValues,
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
fun DogCardButton(expanded: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogCard(modifier: Modifier, dog: Dog) {
    var expandable by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
    ) {

        Row(Modifier.padding(dimensionResource(id = R.dimen.padding_mediumsmall))) {
            Image(
                painter = painterResource(id = dog.img),
                contentDescription = "pug",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(dimensionResource(id = R.dimen.icon_size))
            )
            DogInfo(dog)
            Spacer(modifier = Modifier.weight(1f))
            DogCardButton(expanded = expandable) {
                expandable = !expandable
            }
        }
        if (expandable) {
            DogFood(
                dogFood = dog.favoriteFood, Modifier.padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_medium)
                )
            )
        }

    }
}

@Composable
fun DogInfo(dog : Dog) {
    Column(Modifier.padding(start = 16.dp)) {
        Text(text = dog.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = dog.birth.toYear() + " years old" , fontSize = 16.sp)
    }
}

@Composable
fun DogFood(
    dogFood: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = "Favorite Food", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = dogFood, fontSize = 12.sp)
    }
}


@Composable
@Preview
fun DogListPreview() {
    DogListScreen()
}