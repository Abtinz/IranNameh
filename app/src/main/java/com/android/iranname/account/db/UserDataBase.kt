package com.android.iranname.account.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 5,
    exportSchema = false
)
abstract class UserDataBase:RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object{

        @Volatile private var instance : UserDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDataBase::class.java,
            "userDB"
        ).fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: dbBuilder(context).also {
                instance = it
            }
        }
    }

}