package com.android.iranname.landmarks.ui.model.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.iranname.landmarks.model.LandmarksDC
import com.android.iranname.landmarks.ui.model.screens.LandmarksScreens
import com.android.iranname.landmarks.ui.screen.LandmarksListScreens
import com.android.iranname.landmarks.ui.screen.landmarks.LandmarkScreens
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

@ExperimentalMaterialApi
@Composable
fun LandmarkNavGraph (navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        startDestination = LandmarksScreens.MainMenu.route
    ) {
        composable(route = LandmarksScreens.MainMenu.route) {
            LandmarksListScreens(navController = navHostController)
        }

        composable(
            route = LandmarksScreens.LandmarksScreen.route,
            arguments = listOf(
                navArgument("landmarkJson") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val filtersJson = backStackEntry.arguments?.getString("landmarkJson")
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(LandmarksDC::class.java).lenient()
            val landmark = jsonAdapter.fromJson(filtersJson!!)!!
            LandmarkScreens(
                landmarksDC = landmark,
                navController = navHostController
            )
        }
    }
}