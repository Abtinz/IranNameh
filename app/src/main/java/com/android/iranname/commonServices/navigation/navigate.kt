package com.android.iranname.commonServices.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.account.ui.screen.LogIn
import com.android.iranname.account.ui.screen.SignUp
import com.android.iranname.mainmenu.ui.model.homeScreenRoute
import com.android.iranname.mainmenu.ui.screen.HomePageScreen

@ExperimentalMaterialApi
@Composable
fun AccountNavGraph (navHostController: NavHostController){
NavHost(
navController = navHostController,
startDestination = "logIn"
) {
    composable(route = "SignUp") {
        SignUp(navHostController)
    }

    composable(route = "logIn"){
        LogIn(navHostController)
    }

    composable(route = homeScreenRoute) {
        HomePageScreen()
    }
}
}