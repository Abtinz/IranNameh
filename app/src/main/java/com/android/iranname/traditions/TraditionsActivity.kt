package com.android.iranname.traditions

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.iranname.traditions.ui.model.actionbar.AppActionBar
import com.android.iranname.traditions.ui.model.navigation.TraditionNavGraph
import com.android.iranname.traditions.ui.theme.TraditionalTheme

class TraditionsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TraditionalTheme {
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                rememberCoroutineScope()
                val navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppActionBar("آداب و رسوم")
                    },
                    drawerElevation = 30.dp,
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        TraditionNavGraph(navHostController = navController)
                    }
                }
            }
        }
    }
}