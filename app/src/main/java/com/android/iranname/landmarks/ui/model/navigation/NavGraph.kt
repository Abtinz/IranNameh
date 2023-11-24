package com.android.iranname.landmarks.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.landmarks.ui.model.screens.LandmarksScreens
import com.android.iranname.landmarks.ui.screen.LandmarksListScreens

@ExperimentalMaterialApi
@Composable
fun HomePageNavGraph (navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        startDestination = LandmarksScreens.MainMenu.route
    ) {
        composable(route = LandmarksScreens.MainMenu.route) {
            LandmarksListScreens(navController = navHostController)
        }
    }
}