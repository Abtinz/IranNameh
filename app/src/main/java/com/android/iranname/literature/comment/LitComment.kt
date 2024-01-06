package com.android.iranname.literature.comment

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class LitComment(
    val user_id: Int,
    val literature_id: Int,
    val text: String,
    val created_at: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}