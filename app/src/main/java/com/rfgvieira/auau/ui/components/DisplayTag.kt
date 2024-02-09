package com.rfgvieira.auau.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DisplayTags(list : List<String>) {
    if (list.isNotEmpty()) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(bottom = 12.dp)) {
            list.forEach { item ->
                DisplayTagCard(item)
            }
        }
    }
}

@Composable
fun DisplayTagsEdit(state: SnapshotStateList<String>) {
    if (state.isNotEmpty()) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(bottom = 12.dp)) {
            state.forEach { item ->
                DisplayTagCard(item, state)
            }
        }
    }
}

@Composable
fun DisplayTagCard(text: String, list: SnapshotStateList<String>? = null) {
    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        modifier = Modifier.clickable {if(!list.isNullOrEmpty()){ list.remove(text) }}
    ) {

        Row(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Text(text = text, fontSize = 14.sp)
            list?.let{
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Delete hobby",
                    Modifier.size(20.dp)
                )
            }
        }
    }

}