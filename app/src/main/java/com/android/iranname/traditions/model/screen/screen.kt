package com.android.iranname.traditions.model.screen

const val cityScreenRoute = "CITY"
const val traditionScreenRoute = "TRADITION"


sealed class TraditionScreen(val route: String) {
    object City : TraditionScreen(route = cityScreenRoute)

    object Tradition : TraditionScreen(route = "$traditionScreenRoute/{cityName}") {
        fun passInfo(cityName: String): String {
            return "$traditionScreenRoute/${cityName}"
        }
    }
}