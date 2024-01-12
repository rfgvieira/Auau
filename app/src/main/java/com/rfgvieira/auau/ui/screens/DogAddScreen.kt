package com.rfgvieira.auau.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController

@Composable
fun DogAddScreen(navController: NavHostController) {
    Column (verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End){
        Button(onClick = { navController.popBackStack("doglist", inclusive = false)}, ) {
            Text(text = "Add")
        }
    }
   
}