package com.android.iranname.commonServices.network.response

import com.android.iranname.commonServices.model.CommentDC

data class CommentResponse(
    val success: Boolean,
    val comments: List<CommentDC>
)