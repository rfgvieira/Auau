package com.rfgvieira.auau.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.presentation.components.ButtonIconText
import com.rfgvieira.auau.R

@Composable
fun AboutScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text("About", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("AuauApp is a app create to help dog owners to manage your furry pals.")
        Spacer(Modifier)
        ButtonIconText(
            onClick = { /*TODO*/ },
            icon = painterResource(id = R.drawable.github_logo),
            text = "Project Github",
            modifier = Modifier.fillMaxWidth(0.7f)

        )

        ButtonIconText(
            onClick = { /*TODO*/ },
            icon = painterResource(id = R.drawable.github_logo),
            text = "My Github",
            modifier = Modifier.fillMaxWidth(0.7f)

        )

        ButtonIconText(
            onClick = { /*TODO*/ },
            icon = rememberVectorPainter(image = Icons.Filled.Email),
            text = "My Email",
            modifier = Modifier.fillMaxWidth(0.7f)

        )

        ButtonIconText(
            onClick = { /*TODO*/ },
            icon = painterResource(id = R.drawable.linkedin_logo),
            text = "My Linkedin",
            modifier = Modifier.fillMaxWidth(0.7f)

        )
        Spacer(modifier = Modifier.weight(1f))
        Text( buildAnnotatedString {
            append("Created By: ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Rodrigo Vieira")
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}