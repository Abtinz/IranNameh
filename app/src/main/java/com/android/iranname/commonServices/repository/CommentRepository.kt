package com.android.iranname.commonServices.repository

import com.android.iranname.commonServices.model.CommentDC
import com.android.iranname.commonServices.network.url.CommentService
import io.reactivex.rxjava3.core.Single

class CommentRepository(private val commentService: CommentService) {
    suspend fun getComments(): Single<List<CommentDC>> {
        return commentService.getComments()
    }

    suspend fun postComment(comment: CommentDC): String {
        return commentService.postComment(comment)
    }
}