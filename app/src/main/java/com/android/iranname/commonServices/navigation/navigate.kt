package com.android.iranname.commonServices.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.account.db.User
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.account.ui.screen.AccountPageScreen
import com.android.iranname.account.ui.screen.LogIn
import com.android.iranname.account.ui.screen.SignUp
import com.android.iranname.mainmenu.ui.model.homeScreenRoute
import com.android.iranname.mainmenu.ui.screen.HomePageScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AccountNavGraph(navHostController: NavHostController) {
    val context = LocalContext.current
    var user: User? = null
    CoroutineScope(Dispatchers.Default).launch {
        try {
            user = UserDataBase(context).getUserDao().getFirstUser()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    NavHost(
        navController = navHostController,
        startDestination = if (user == null )"logIn" else "account"
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
            AccountPageScreen(user)
        }
    }
}