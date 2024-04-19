package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.R


/*TODO: Adidicionar sobre*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(navigateToAbout: () -> Unit, navigateToList: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = Modifier,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { navigateToList() }) {
                Image(
                    painter = painterResource(id = R.drawable.paw_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.icon_size))
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )

                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            IconButton(onClick = { navigateToAbout()}) {
                Icon(
                    Icons.Filled.QuestionMark,
                    contentDescription = "About Icon",
                    Modifier
                        .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface), CircleShape)
                        .padding(2.dp)
                )
            }
        }
    )
}


