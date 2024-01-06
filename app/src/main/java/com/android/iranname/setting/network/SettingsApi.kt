package com.android.iranname.setting.network

import com.android.iranname.commonServices.network.response.PostResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SettingsApiService {
    @GET("accounts/about/")
    suspend fun aboutUs(): PostResponse

    @FormUrlEncoded
    @POST("accounts/help/")
    suspend fun help(
        @Field("help") help:String
    ): PostResponse
}