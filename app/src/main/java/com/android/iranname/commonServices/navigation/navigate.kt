package com.android.iranname.commonServices.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.iranname.account.ui.screen.LogIn
import com.android.iranname.account.ui.screen.SignUp

@RequiresApi(Build.VERSION_CODES.O)
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
}
}