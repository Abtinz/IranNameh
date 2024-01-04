package com.android.iranname.shops.db.faqs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProductFAQsDC::class],
    version = 3,
    exportSchema = false
)
abstract class ProductFAQsDataBase:RoomDatabase() {
    abstract fun getProductFAQsDao(): ProductFAQsDao

    companion object{

        @Volatile private var instance : ProductFAQsDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProductFAQsDataBase::class.java,
            "productFAQsDB"
        )
            .fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: dbBuilder(context).also {
                instance = it
            }
        }
    }

}