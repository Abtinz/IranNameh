package com.android.iranname.literature.comment

import com.android.iranname.commonServices.network.response.PostResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LitCommentService {
    @GET("literature/getComments/")
    suspend fun getComments(@Query("literature_id")literature_id: Int): LitCommentResponse

    @FormUrlEncoded
    @POST("literature/comments/")
    suspend fun postComment(
        @Field("comment") comment: LitComment
    ): PostResponse
}