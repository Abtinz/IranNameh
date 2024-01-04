package com.android.iranname.traditions.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.iranname.traditions.db.CityTraditionsDC
import com.android.iranname.traditions.ui.model.screen.TraditionScreen
import com.android.iranname.traditions.ui.screens.CityScreen
import com.android.iranname.traditions.ui.screens.CityTraditionsScreen
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.net.URLDecoder

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

        composable(
            route = TraditionScreen.Tradition.route,
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val cityJson = backStackEntry.arguments?.getString("city")

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val decodedInfo = URLDecoder.decode(cityJson, "UTF-8")
            val jsonAdapter = moshi.adapter(CityTraditionsDC::class.java).lenient()
            val city = jsonAdapter.fromJson(decodedInfo!!)!!
            CityTraditionsScreen(
                city = city
            )
        }
    }
}