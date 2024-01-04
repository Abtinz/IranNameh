package com.android.iranname.account.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    val user_id :String,
//    val email :String,
//    var imageUri :String,
//    val token :String,
    val uuid :Int,
//    val isGhost:Boolean?
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}