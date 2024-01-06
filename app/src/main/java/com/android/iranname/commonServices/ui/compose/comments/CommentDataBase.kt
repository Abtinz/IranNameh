package com.android.iranname.commonServices.ui.compose.comments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.iranname.commonServices.model.CommentDC

@Database(
    entities = [CommentDC::class],
    version = 1,
    exportSchema = false
)
abstract class CommentDataBase: RoomDatabase() {
    abstract fun getCommentDao(): CommentDao

    companion object{

        @Volatile private var instance : CommentDataBase? = null

        private val LOCK = Any()

        private fun dbBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CommentDataBase::class.java,
            "commentDao"
        ).fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: dbBuilder(context).also {
                instance = it
            }
        }
    }

}