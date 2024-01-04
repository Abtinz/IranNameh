package com.android.iranname.shops.db.faqs

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ProductFAQsDC(
    val question : String,
    val  answer :String,
    val productId :Int
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}
