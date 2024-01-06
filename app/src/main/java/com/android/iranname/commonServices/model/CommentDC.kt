package com.android.iranname.commonServices.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CommentDC(
    val user_id: Int,
    val landmark_id: Int,
    val text:String,
    val created_at: String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}
