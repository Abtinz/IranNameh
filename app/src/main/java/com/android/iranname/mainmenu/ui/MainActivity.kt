package com.android.iranname.mainmenu.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.android.iranname.commonServices.ui.compose.actionbar.HomeActionBar
import com.android.iranname.mainmenu.ui.model.HomePageNavGraph
import com.android.iranname.mainmenu.ui.theme.IranNameTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IranNameTheme {
                HomePageSetUp()
            }
        }
    }
}


@Composable
@ExperimentalMaterialApi
fun HomePageSetUp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeActionBar()
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            HomePageNavGraph(navHostController =navController )
        }
    }
}
