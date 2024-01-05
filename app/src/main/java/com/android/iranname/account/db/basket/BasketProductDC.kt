package com.android.iranname.account.db.basket

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**this class will help us to implement and save shop basket products models for database Dao
 *
 */
@Entity
data class BasketProductDC(
    val name: String,
    val image:String,
    val description: String,
    val price: Double
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}

