package com.rfgvieira.auau.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.utils.DateUtils.Companion.toYear

@Composable
fun DogCard(modifier: Modifier, dog: Dog, navigateToDetails: (Dog) -> Unit, onDeleteDog : (Dog) -> Unit) {
    var expandable by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Row(
            Modifier
                .padding(dimensionResource(id = R.dimen.padding_mediumsmall))
                .clickable { expandable = !expandable }) {

            DogImage(
                dog = dog, Modifier
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
            DogPlus(
                Modifier.padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium)
                ), navigateToDetails
                , dog, showDialog
            )
        }

    }

    if(showDialog.value) DialogDeleteDog(dog = dog , showDialog =  showDialog, onDeleteDog)
}

@Composable
fun DogInfo(dog: Dog) {
    Column(Modifier.padding(start = 16.dp)) {
        Text(text = dog.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = dog.birth.toYear() + " years old", fontSize = 16.sp)
    }
}

@Composable
fun DogPlus(
    modifier: Modifier = Modifier,
    navigateToDetails: (Dog) -> Unit,
    dog: Dog,
    showDialog: MutableState<Boolean>
) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        Button(
            onClick = { showDialog.value = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text(text = "Excluir")
        }
        Button(onClick = { navigateToDetails(dog) }) {
            Text(text = "See more")
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

