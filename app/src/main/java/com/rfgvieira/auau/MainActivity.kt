package com.rfgvieira.auau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rfgvieira.auau.ui.theme.AuauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Splash()
        }
    }
}

@Composable
fun Splash() {
    Surface(Modifier.fillMaxSize()) {
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AuauTheme {
        Splash()
    }
}