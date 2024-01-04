package com.android.iranname.traditions.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CityTraditionsDC::class],
    version = 1,
    exportSchema = false
)
abstract class CityTraditionsDataBase:RoomDatabase() {
    abstract fun getCityTraditionsDao(): CityTraditionsDao

    companion object{

        @Volatile private var instance : CityTraditionsDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CityTraditionsDataBase::class.java,
            "cityTraditionsDB"
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