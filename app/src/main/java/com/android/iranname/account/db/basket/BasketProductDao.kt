package com.android.iranname.account.db.basket

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BasketProductDao {

    @Insert
    suspend fun newProduct(basketProductDC: BasketProductDC)

    @Update
    suspend fun editProduct(basketProductDC: BasketProductDC)

    @Delete
    suspend fun deleteProduct(basketProductDC: BasketProductDC)

    @Query("SELECT * FROM basketProductDC")
    suspend fun getBasket(): List<BasketProductDC>

}