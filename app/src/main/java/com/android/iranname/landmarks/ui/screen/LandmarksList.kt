package com.android.iranname.landmarks.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.iranname.commonServices.ui.compose.reload.Reload
import com.android.iranname.landmarks.ui.screen.landmarks.LandmarksCardView
import com.android.iranname.landmarks.ui.viewmodel.LandmarkListViewModel

@Composable
fun LandmarksListScreens(navController: NavController) {
    val viewModel = viewModel(LandmarkListViewModel::class.java)

    val landmarks by viewModel.landmarksList.collectAsState()
    val loadingState by viewModel.loadStatus.collectAsState()

    viewModel.landmarksProvider(
        context = LocalContext.current
    )

    if(loadingState == "loading"){
        Reload()
    }else{
        LazyColumn{
            items(landmarks){it ->
                LandmarksCardView(
                    landmarksDC = it,
                    navController = navController
                )
            }
        }
    }
}