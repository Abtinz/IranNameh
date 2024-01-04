package com.android.iranname.shops.db.faqs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductFAQsDao {

    @Insert
    suspend fun newFAQs(productFAQsDC: ProductFAQsDC)

    @Query("SELECT * FROM productFAQsDC")
    suspend fun getAllFAQs(): List<ProductFAQsDC>

}