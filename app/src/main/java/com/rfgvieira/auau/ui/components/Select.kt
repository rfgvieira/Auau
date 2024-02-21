package com.rfgvieira.auau.ui.components

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Select(state: SnapshotStateList<String>, list: List<String>, modifier : Modifier) {


    var selectedElement by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val context =LocalContext.current

    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }) {

            TextField(value = selectedElement, onValueChange = {},
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Sharp.ArrowDropUp else Icons.Sharp.ArrowDropDown,
                        contentDescription = "Select Arrow"
                    )
                }, modifier = modifier.padding(16.dp).menuAnchor(), placeholder = { Text("Hobbies") }, readOnly = true)

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {

                            selectedElement = item
                            if(!state.contains(item))
                                state.add(item)
                            else
                                Toast.makeText(context,"Hobby already added", Toast.LENGTH_SHORT).show()
                            expanded = false
                        })

                }
            }
        }
        DisplayTagsEdit(state, Modifier.padding(bottom = 12.dp))
    }
}



@Composable
@Preview(showBackground = true)
fun SelectPreview() {
    val state = remember {
        mutableStateListOf<String>()
    }
    val hobbiesList = listOf("Dig", "Walk", "Bark", "Play", "Sleep", "Pat")

    state.addAll(arrayOf("Batata", "Arroz", "Samambaia", "Bahia"))
    Select(state = state, hobbiesList, Modifier.padding(16.dp))
}
