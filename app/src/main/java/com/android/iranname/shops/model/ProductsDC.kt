package com.android.iranname.shops.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**this class will help us to implement and save shop products models for database Dao
 *
 */
@Entity
data class ProductsDC(
    val name : String,
    val image :String,
    val description : String,
    val price:Float
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}
