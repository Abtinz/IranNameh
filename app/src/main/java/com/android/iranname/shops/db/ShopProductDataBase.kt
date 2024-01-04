package com.android.iranname.shops.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.iranname.shops.model.ProductsDC

@Database(
    entities = [ProductsDC::class],
    version = 1,
    exportSchema = false
)
abstract class ShopProductDataBase:RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object{

        @Volatile private var instance : ShopProductDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ShopProductDataBase::class.java,
            "shopProductDB"
        ).fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: dbBuilder(context).also {
                instance = it
            }
        }
    }

}