package com.android.iranname.literature.comment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [LitComment::class],
    version = 1,
    exportSchema = false
)
abstract class LitDataBase: RoomDatabase() {
    abstract fun getLitDao(): LitDao

    companion object{

        @Volatile private var instance : LitDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LitDataBase::class.java,
            "LitDao"
        ).fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: dbBuilder(context).also {
                instance = it
            }
        }
    }

}