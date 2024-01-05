package com.android.iranname.account.db.basket

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BasketProductDC::class],
    version = 1,
    exportSchema = false
)
abstract class BasketProductDataBase:RoomDatabase() {
    abstract fun getBasketProductDao(): BasketProductDao

    companion object{

        @Volatile private var instance : BasketProductDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BasketProductDataBase::class.java,
            "basketProductDao"
        ).fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: dbBuilder(context).also {
                instance = it
            }
        }
    }

}