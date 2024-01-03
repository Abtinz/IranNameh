package com.android.iranname.shops.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.iranname.shops.model.ProductsDC

@Dao
interface ProductDao {

    @Insert
    suspend fun newProduct(productsDC: ProductsDC)

    @Update
    suspend fun editProduct(productsDC: ProductsDC)

    @Delete
    suspend fun deleteProduct(productsDC: ProductsDC)

    @Query("SELECT * FROM productsdc")
    suspend fun getAllProducts(): List<ProductsDC>

}