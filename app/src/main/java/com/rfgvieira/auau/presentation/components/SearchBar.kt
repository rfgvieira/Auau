package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier,
    onSearch: (text: String) -> Unit,
    text: String
) {
    Row(
        modifier = modifier.border(
            width = 2.dp,
            shape = ShapeDefaults.Small,
            color = Color.Black
        )
    ) {
        TextField(
            value = text,
            onValueChange = {
                onSearch(it)
            },
            singleLine = true,
            trailingIcon = {
                Icon(imageVector = Icons.Sharp.Search, contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
    }
}

@Preview()
@Composable
fun SearchBarPreview() {
    SearchBar(Modifier, {}, "text")
}