package com.android.iranname.commonServices.ui.compose.comments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.iranname.commonServices.model.CommentDC

@Dao
interface CommentDao {

    @Insert
    suspend fun newComment(commentDC: CommentDC)

    @Update
    suspend fun editComment(commentDC: CommentDC)

    @Delete
    suspend fun deleteComment(commentDC: CommentDC)

    @Query("SELECT * FROM commentDC Where landmark_id = :id ")
    suspend fun getComments(id: Int): List<CommentDC>

}