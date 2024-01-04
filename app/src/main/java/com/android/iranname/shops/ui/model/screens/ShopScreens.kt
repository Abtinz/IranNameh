package com.android.iranname.shops.ui.model.screens

import com.android.iranname.shops.model.ProductsDC
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLEncoder

sealed class ShopScreens(val route:String) {
    object MainMenu : ShopScreens(route = "home")
    object ProductScreen: ShopScreens(route = "product/page/{productJson}"){
        fun passInfo(productsDC: ProductsDC):String{
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(ProductsDC::class.java).lenient()
            val productJson = URLEncoder.encode(jsonAdapter.toJson(productsDC), "UTF-8")
            return "product/page/${productJson}"
        }
    }

}