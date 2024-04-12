package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDog(
    modifier: Modifier,
    navigateBack: () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {


    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton( onClick = { navigateBack() }
            ) {
                Icon(modifier = Modifier
                    .clip(CircleShape)
                    .size(32.dp),
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = "Back to List",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            scrolledContainerColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = title,
        actions = actions
    )

}

@Preview
@Composable
fun TopBarDogPreview() {
    TopBarDog(modifier = Modifier, navigateBack = {  }, title = { Text("Baba") }) {

    }
}

