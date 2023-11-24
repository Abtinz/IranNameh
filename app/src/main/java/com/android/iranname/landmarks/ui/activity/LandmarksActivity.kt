package com.android.iranname.landmarks.ui.activity

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.android.iranname.R
import com.android.iranname.commonServices.ui.compose.actionbar.AppActionBar
import com.android.iranname.landmarks.ui.model.navigation.LandmarkNavGraph
import com.android.iranname.landmarks.ui.theme.LandmarksTheme

class LandmarksActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandmarksTheme {
                LandmarksPageSetUp()
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun LandmarksPageSetUp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppActionBar(title = stringResource(id = R.string.title_activity_landmarks))
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            LandmarkNavGraph(navHostController = navController )
        }
    }
}
