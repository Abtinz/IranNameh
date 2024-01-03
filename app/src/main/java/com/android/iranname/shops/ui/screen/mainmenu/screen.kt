package com.android.iranname.shops.ui.screen.mainmenu

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.iranname.commonServices.network.quality.UserNetworkChecker
import com.android.iranname.commonServices.ui.compose.error.network.NetworkIsGoneScreen
import com.android.iranname.commonServices.ui.compose.error.server.SomethingWentWrongScreen
import com.android.iranname.commonServices.ui.compose.reload.Reload
import com.android.iranname.shops.ui.screen.product.ProductCardView
import com.android.iranname.shops.viewmodel.mainmenu.ShopMainMenuVM

@Composable
fun ShopMainMenuScreen(navController: NavHostController) {

    val context = LocalContext.current
    val isInternetAvailable = remember{
        mutableStateOf(UserNetworkChecker(context).checkNetwork)
    }
    if(isInternetAvailable.value){
        val viewModel = viewModel(ShopMainMenuVM::class.java)

        val productList by viewModel.products.collectAsState()
        val loadingState by viewModel.loadStatus.collectAsState()

        viewModel.loadProducts(
            context = LocalContext.current
        )

        if(loadingState == "loading"){
            Reload()
        } else if(loadingState == "exception") {
            SomethingWentWrongScreen()
        } else {
            LazyColumn{
                items(productList){it ->
                    ProductCardView(
                        productsDC = it,
                        navController = navController
                    )
                }
            }
        }
    }else{
        NetworkIsGoneScreen{
            isInternetAvailable.value = true
        }
    }
}
