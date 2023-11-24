package com.android.iranname.literature.ui.model.screen


const val literatureScreenRoute = "LITERATURE"
const val poetScreenRoute = "POET"
const val poetryScreenRoute = "POETRY"


sealed class LiteratureScreen(val route: String) {
    object Literature : LiteratureScreen(route = literatureScreenRoute)

    object Poet : LiteratureScreen(route = "$poetScreenRoute/{poetName}") {
        fun passInfo(poetName: String): String {
            return "$poetScreenRoute/${poetName}"
        }
    }

    object Poetry : LiteratureScreen(route = "$poetryScreenRoute/{poetryName}") {
        fun passInfo(poetryName: String, poetName: String): String {
            return "$poetryScreenRoute/${poetryName}"
        }
    }
}