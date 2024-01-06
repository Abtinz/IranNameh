package com.android.iranname.setting.ui.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.setting.ui.screens.AboutUsSettingScreen
import com.android.iranname.setting.ui.screens.ContactUsSettingScreen
import com.android.iranname.setting.ui.screens.SettingHelpScreen
import com.android.iranname.setting.ui.screens.SettingScreen

@Composable
fun SettingNavGraph(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = SettingScreens.MainMenu.route
    ) {

        composable(route = SettingScreens.MainMenu.route) {
            SettingScreen(navController = navHostController)
        }

        composable(route = SettingScreens.Help.route) {
            SettingHelpScreen()
        }

        composable(route = SettingScreens.AboutUs.route) {
            AboutUsSettingScreen()
        }

        composable(route = SettingScreens.ContactUs.route) {
            ContactUsSettingScreen(navController = navHostController)
        }
    }
}