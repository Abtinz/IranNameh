package com.android.iranname.literature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.iranname.commonServices.ui.compose.actionbar.AppActionBar
import com.android.iranname.literature.ui.model.navigation.LiteraturePageNavGraph
import com.android.iranname.literature.ui.theme.IranNameTheme

class LiteratureActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IranNameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scaffoldState =
                        rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                    rememberCoroutineScope()
                    val navController = rememberNavController()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            AppActionBar("ادبیات فارسی")
                        },
                        drawerElevation = 30.dp,
                    ) { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            LiteraturePageNavGraph(navHostController = navController)
                        }
                    }
                }
            }
        }
    }
}
