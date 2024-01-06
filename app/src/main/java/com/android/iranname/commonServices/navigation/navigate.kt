package com.android.iranname.commonServices.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.account.ui.screen.AccountPageScreen
import com.android.iranname.account.ui.screen.LogIn
import com.android.iranname.account.ui.screen.SignUp
import com.android.iranname.account.viewModel.LogInViewModel
import com.android.iranname.mainmenu.ui.model.homeScreenRoute
import com.android.iranname.mainmenu.ui.screen.HomePageScreen

@ExperimentalMaterialApi
@Composable
fun AccountNavGraph(navHostController: NavHostController) {
    val context = LocalContext.current
    val viewModel: LogInViewModel = viewModel()
    viewModel.loadUser(context)
    val loadedUser by viewModel.loadedUser.collectAsState()
    NavHost(
        navController = navHostController,
        startDestination = if (loadedUser == null )"logIn" else "account"
    ) {

        composable(route = "SignUp") {
            SignUp(navHostController)
        }

        composable(route = "logIn") {
            LogIn(navHostController)
        }

        composable(route = homeScreenRoute) {
            HomePageScreen()
        }

        composable(route = "account") {
            AccountPageScreen(loadedUser, navHostController)
        }
    }
}