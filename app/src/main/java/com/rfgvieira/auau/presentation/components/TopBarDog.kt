package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
/*TODO: Adjuste title position*/
@Composable
fun TopBarDog(
    modifier: Modifier,
    navigateBack: () -> Unit,
    title: @Composable () -> Unit,
    delete: @Composable () -> Unit = {},
    edit: @Composable () -> Unit = {}) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.weight(3f)) {
            Icon(
                imageVector = Icons.Sharp.ArrowBack,
                contentDescription = "Back to List",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(32.dp)
                    .clickable { navigateBack() }
            )
        }
        Row(modifier = Modifier.weight(2f)) {
            title()
        }

        edit()

        delete()

    }
}

