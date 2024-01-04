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

    @Query("SELECT * FROM productFAQsDC WHERE productId = :productId")
    fun getFAQsByProductId(productId: Int): List<ProductFAQsDC>

}