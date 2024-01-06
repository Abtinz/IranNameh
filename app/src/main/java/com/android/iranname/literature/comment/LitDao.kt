package com.android.iranname.literature.comment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LitDao {

    @Insert
    suspend fun newComment(commentDC: LitComment)

    @Update
    suspend fun editComment(commentDC: LitComment)

    @Delete
    suspend fun deleteComment(commentDC: LitComment)

    @Query("SELECT * FROM LitComment Where literature_id = :id ")
    suspend fun getComments(id: Int): List<LitComment>

}