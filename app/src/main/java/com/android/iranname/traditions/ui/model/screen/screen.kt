package com.android.iranname.traditions.ui.model.screen

import com.android.iranname.traditions.db.CityTraditionsDC
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLEncoder

const val cityScreenRoute = "CITY"
const val traditionScreenRoute = "TRADITION"


sealed class TraditionScreen(val route: String) {
    object City : TraditionScreen(route = cityScreenRoute)

    object Tradition : TraditionScreen(route = "$traditionScreenRoute/{city}") {
        fun passInfo(city: CityTraditionsDC): String {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(CityTraditionsDC::class.java).lenient()
            val cityJson = URLEncoder.encode(jsonAdapter.toJson(city), "UTF-8")
            return "$traditionScreenRoute/${cityJson}"
        }
    }
}