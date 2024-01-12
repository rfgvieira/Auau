package com.rfgvieira.auau.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rfgvieira.auau.R
import com.rfgvieira.auau.ui.screens.DogAddScreen
import com.rfgvieira.auau.ui.screens.DogListScreen
import com.rfgvieira.auau.ui.theme.AuauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val showFAB = remember { mutableStateOf(true) }
            val isDark = false

            AuauTheme(darkTheme = isDark) {
                AuauApp(showFab = showFAB.value,onFabClick = {navController.navigate("dogadd")}){
                    NavHost(navController = navController, startDestination = "doglist"){
                        composable("doglist"){
                            showFAB.value = true
                            DogListScreen()
                        }
                        composable("dogadd"){
                            showFAB.value = false
                            DogAddScreen(navController)
                        }
                    }
                }
            }


        }
    }
}
@Composable
fun AuauApp(
    showFab : Boolean,
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = { TopBar(modifier = Modifier) },
        floatingActionButton = {
            if(showFab){
                FloatingActionButton(onClick = onFabClick, shape = CircleShape, containerColor = MaterialTheme.colorScheme.primary) {
                    Text(text = "+")
                }
            }

        }

    ) {
        Box(modifier = Modifier.padding(it)){
            content()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    )
}
