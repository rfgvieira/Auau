package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.R

@Composable
fun ButtonIconText(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ),
    modifier: Modifier
) {

    OutlinedButton(
        onClick = { onClick() },
        colors = colors,
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Icon(
                painter = icon,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = 16.dp)
            )

            Text(text = text, modifier = modifier.weight(1f), fontSize = 20.sp, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonIconTextPreview() {
    ButtonIconText(
        onClick = { },
        icon = painterResource(id = R.drawable.paw_logo),
        text = "Teste",
        modifier = Modifier.fillMaxWidth(0.9f)
    )

}


