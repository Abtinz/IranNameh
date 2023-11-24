package com.android.iranname.literature.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.mainmenu.ui.screen.HomePageScreen

const val homeScreenRoute = "HOME"

@ExperimentalMaterialApi
@Composable
fun LiteraturePageNavGraph (navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        startDestination = homeScreenRoute
    ) {
        composable(route = homeScreenRoute) {
            HomePageScreen()
        }
    }
}