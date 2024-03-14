package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.domain.model.Dog

@Composable
fun DialogDeleteDog(dog: Dog, showDialog: MutableState<Boolean>, onDeleteDog : (Dog) -> Unit, navigateBack: (() -> Unit)? = null) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Sharp.Delete,
                contentDescription = "Delete Dog Icon",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(50.dp)
            )
        },
        title = { Text(text = "Delete Dog") },
        text = {
            Text(
                text = "Are you sure you want to delete ${dog.name} from app",
                fontSize = 18.sp
            )
        },
        onDismissRequest = { showDialog.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteDog(dog)
                    showDialog.value= false
                    navigateBack?.let{
                        it()
                    }
                }
            ) {
                Text("Yes", fontSize = 16.sp)
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialog.value = false }) {
                Text("No", color = MaterialTheme.colorScheme.error, fontSize = 16.sp)
            }
        }
    )
}