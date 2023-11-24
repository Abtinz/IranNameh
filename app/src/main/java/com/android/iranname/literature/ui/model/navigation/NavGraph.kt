package com.android.iranname.literature.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.literature.ui.model.screen.LiteratureScreen
import com.android.iranname.literature.ui.screens.LiteraturePageScreen
import com.android.iranname.literature.ui.screens.PoetPageScreen
import com.android.iranname.literature.ui.screens.PoetryPageScreen



@ExperimentalMaterialApi
@Composable
fun LiteraturePageNavGraph(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = LiteratureScreen.Literature.route
    ) {
        composable(route = LiteratureScreen.Literature.route) {
            LiteraturePageScreen(navHostController)
        }

        composable(route = LiteratureScreen.Poet.route) { backStackEntry ->
            val poetName = backStackEntry.arguments?.getString("poetName")

            if (poetName != null) {
                PoetPageScreen(poetName = poetName, navController = navHostController)
            }
        }

        composable(route = LiteratureScreen.Poetry.route) { backStackEntry ->
            val poetryName = backStackEntry.arguments?.getString("poetryName")
            val poetName = backStackEntry.arguments?.getString("poetName")

            if (poetryName != null && poetName != null) {
                PoetryPageScreen(poetryName = poetryName, poetName = poetName)
            }
        }
    }
}