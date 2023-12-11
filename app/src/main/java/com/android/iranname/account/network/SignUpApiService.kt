package com.android.iranname.account.network

import com.android.iranname.commonServices.network.response.AuthenticationResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpApiService {
    @FormUrlEncoded
    @POST("accounts/register/")
    suspend fun userSignUp(
        @Field("username") username: String,
        @Field("password1") password1: String,
        @Field("email") email: String,
        @Field("password2") password2: String,
    ): AuthenticationResponse
}
