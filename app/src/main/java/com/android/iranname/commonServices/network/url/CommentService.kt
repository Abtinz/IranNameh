package com.android.iranname.commonServices.network.url

import com.android.iranname.commonServices.model.CommentDC
import com.android.iranname.commonServices.network.response.CommentResponse
import com.android.iranname.commonServices.network.response.PostResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {
    @GET("landmarks/getComments/")
    suspend fun getComments(@Query("landmark_id")landmark_id: Int): CommentResponse

    @FormUrlEncoded
    @POST("landmarks/comments/")
    suspend fun postComment(
        @Field("comment") comment: CommentDC
    ): PostResponse
}