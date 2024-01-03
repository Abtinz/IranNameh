package com.android.iranname.traditions.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.traditions.ui.model.screen.TraditionScreen
import com.android.iranname.traditions.ui.screens.CityScreen
import com.android.iranname.traditions.ui.screens.TraditionScreen

@ExperimentalMaterialApi
@Composable
fun TraditionNavGraph(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = TraditionScreen.City.route
    ) {
        composable(route = TraditionScreen.City.route) {
            CityScreen(navHostController)
        }

        composable(route = TraditionScreen.Tradition.route) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName")

            if (cityName != null) {
                TraditionScreen(cityName = cityName)
            }
        }
    }
}