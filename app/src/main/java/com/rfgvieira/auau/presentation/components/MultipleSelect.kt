package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.material.icons.sharp.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultipleSelect(
    text : String,
    list: List<String>,
    modifier: Modifier,
    onSelectedItem: (String) -> Unit
) {
    var selectedElement by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    

    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }) {

            TextField(value = selectedElement,
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Sharp.ArrowDropUp else Icons.Sharp.ArrowDropDown,
                        contentDescription = "Select Arrow"
                    )
                },
                modifier = modifier
                    .padding(16.dp)
                    .menuAnchor(),
                placeholder = { Text(text) },
                readOnly = true
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {

                            selectedElement = item
                            onSelectedItem(item)
                            expanded = false
                        })

                }
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun SelectPreview() {
    val hobbiesList = listOf("Dig", "Walk", "Bark", "Play", "Sleep", "Pat")
    MultipleSelect( "Hobbies", hobbiesList, Modifier.padding(16.dp), {})
}
