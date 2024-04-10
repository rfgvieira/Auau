package com.rfgvieira.auau.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfgvieira.auau.R
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog

import com.rfgvieira.auau.presentation.components.DialogDeleteDog
import com.rfgvieira.auau.presentation.components.DisplayTags
import com.rfgvieira.auau.presentation.components.DogImage
import com.rfgvieira.auau.presentation.components.TopBarDog
import com.rfgvieira.auau.presentation.theme.AuauTheme
import com.rfgvieira.auau.presentation.viewmodel.DogViewModel

//Tela para modificar um cachorro já existente
@Composable
fun DogDetailsScreen(
    dog: Dog,
    viewModel: DogViewModel,
    navigateToEdit: () -> Unit,
    navigateBack: () -> Unit
) {

    val showDialog = remember { mutableStateOf(false) }

    Column {
        TopBarDog(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            navigateBack = navigateBack,
            title = {Text("Baba", color = Color.White)},
            delete = {
                Icon(
                    imageVector = Icons.Sharp.Delete,
                    contentDescription = "Delete Dog",
                    modifier = Modifier
                        .weight(1f)
                        .size(32.dp)
                        .clickable { showDialog.value = true },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            edit = {
                Icon(
                    imageVector = Icons.Sharp.Edit,
                    contentDescription = "Edit Info",
                    modifier = Modifier
                        .weight(1f)
                        .size(32.dp)
                        .clickable { navigateToEdit() },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DogImage(
                dog = dog, modifier = Modifier
                    .clip(CircleShape)
                    .size(dimensionResource(id = R.dimen.image_size))
                    .border(width = 1.dp, Color.Black, CircleShape)
            )

            Text(text = dog.name, fontSize = 24.sp, fontWeight = FontWeight.W700)
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 32.dp)
            ) {
                Text(text = "Birthdate", fontSize = 20.sp, fontWeight = FontWeight.W500)
                Text(text = dog.birth, fontSize = 16.sp)

                dog.favoriteFood?.let {
                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        Text(text = "Favorite Food", fontSize = 20.sp, fontWeight = FontWeight.W500)
                        Text(text = it, fontSize = 16.sp)
                    }
                }

                dog.hobbies?.let {
                    Column(
                        modifier = Modifier.padding(top = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Hobbies", fontSize = 20.sp, fontWeight = FontWeight.W500)
                        DisplayTags(it, Modifier.padding(bottom = 12.dp))
                    }
                }


            }
        }
    }


    if (showDialog.value) DialogDeleteDog(
        dog,
        showDialog,
        { deletedDog -> viewModel.deleteDog(deletedDog) },
        navigateBack
    )


}


@Composable
@Preview(showBackground = true)
fun DogDetailsPreview() {
    AuauTheme {
        DogDetailsScreen(DogDAO.dogsList()[0], viewModel(), {}, {})
    }

}