package com.rfgvieira.auau.presentation.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfgvieira.auau.R
import com.rfgvieira.auau.presentation.components.ButtonIconText


@Composable
fun AboutScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        val context = LocalContext.current

        Text("About", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("AuauApp is a app create to help dog owners to manage your furry pals.")

        Spacer(Modifier)

        ButtonIconText(
            onClick = { openProjectGithub(context) },
            icon = painterResource(id = R.drawable.github_logo),
            text = "Project Github",
            modifier = Modifier.fillMaxWidth(0.7f)

        )

        ButtonIconText(
            onClick = { openCreatorGithub(context) },
            icon = painterResource(id = R.drawable.github_logo),
            text = "My Github",
            modifier = Modifier.fillMaxWidth(0.7f)

        )

        ButtonIconText(
            onClick = { openCreatorEmail(context) },
            icon = rememberVectorPainter(image = Icons.Filled.Email),
            text = "My Email",
            modifier = Modifier.fillMaxWidth(0.7f)

        )

        ButtonIconText(
            onClick = { openCreatorLinkedin(context) },
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

fun openProjectGithub(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rfgvieira/Auau"))
    try{
        context.startActivity(intent)
    } catch (e : Exception){
        Log.i("github_exception", e.message ?: "")
    }
}

fun openCreatorGithub(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rfgvieira/"))
    try{
        context.startActivity(intent)
    } catch (e : Exception){
        Log.i("github_exception", e.message ?: "")
    }
}

fun openCreatorEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, arrayOf("rfgvieira.0@gmail.com"))
    }
    try{
        context.startActivity(intent)
    } catch (e : Exception){
        Log.i("email_exception", e.message ?: "")
    }
}


fun openCreatorLinkedin(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rfgvieira/"))
    try{
        context.startActivity(intent)
    } catch (e : Exception){
        Log.i("linkedin_exception", e.message ?: "")
    }
}





@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}