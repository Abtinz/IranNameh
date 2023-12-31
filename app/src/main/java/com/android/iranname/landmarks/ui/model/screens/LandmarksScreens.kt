package com.android.iranname.landmarks.ui.model.screens

import com.android.iranname.landmarks.model.LandmarksDC
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLEncoder

sealed class LandmarksScreens(val route:String) {
    object MainMenu : LandmarksScreens(route = "home")
    object LandmarksScreen:LandmarksScreens(route = "landmarks/page/{landmarkJson}"){
        fun passInfo(landmarks: LandmarksDC):String{
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(LandmarksDC::class.java).lenient()
            val landmarkJson = URLEncoder.encode(jsonAdapter.toJson(landmarks), "UTF-8")

            return "landmarks/page/${landmarkJson}"
        }
    }

}