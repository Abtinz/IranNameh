package com.android.iranname.shops.ui.model.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.iranname.shops.model.ProductsDC
import com.android.iranname.shops.ui.model.screens.ShopScreens
import com.android.iranname.shops.ui.screen.mainmenu.ShopMainMenuScreen
import com.android.iranname.shops.ui.screen.product.ShopProductScreen
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.net.URLDecoder

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
@Composable
fun ShopNavGraph (navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        startDestination = ShopScreens.MainMenu.route
    ) {
        composable(route = ShopScreens.MainMenu.route) {
            ShopMainMenuScreen(navController = navHostController)
        }

        composable(
            route = ShopScreens.ProductScreen.route,
            arguments = listOf(
                navArgument("productJson") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val productJson = backStackEntry.arguments?.getString("productJson")

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val decodedInfo = URLDecoder.decode(productJson, "UTF-8")
            val jsonAdapter = moshi.adapter(ProductsDC::class.java).lenient()
            val product = jsonAdapter.fromJson(decodedInfo!!)!!
            ShopProductScreen(
                product = product,
                navHostController
            )
        }
    }
}


