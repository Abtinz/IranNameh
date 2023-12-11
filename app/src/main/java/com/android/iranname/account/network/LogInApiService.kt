package com.android.iranname.account.network

import com.android.iranname.commonServices.network.response.AuthenticationResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LogInApiService {
    @FormUrlEncoded
    @POST("accounts/login/")
    suspend fun userLogIn(
        @Field("username") username: String,
        @Field("password") password: String
    ): AuthenticationResponse
}