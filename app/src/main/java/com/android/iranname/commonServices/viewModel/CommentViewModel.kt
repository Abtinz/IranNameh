package com.android.iranname.commonServices.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.User
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.commonServices.model.CommentDC
import com.android.iranname.commonServices.network.RetrofitClient.retrofit
import com.android.iranname.commonServices.network.url.CommentService
import com.android.iranname.commonServices.ui.compose.comments.CommentDataBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommentViewModel : ViewModel() {

    private val _commentsState  = MutableStateFlow<List<CommentDC>>(emptyList())
    val commentsState: StateFlow<List<CommentDC>> get() = _commentsState

    private val _commentsState2  = MutableStateFlow<List<CommentDC>>(emptyList())
    val commentsState2: StateFlow<List<CommentDC>> get() = _commentsState2

    private val _addCommentState = MutableStateFlow("")
    val addCommentState: StateFlow<String> get() = _addCommentState



    // Launch the coroutine when needed
    fun fetchComments(landmark_id: Int, context: Context) {
        viewModelScope.launch {
            try {
                //building api service with its headers and basic url and instances
                _commentsState2.value = CommentDataBase(context).getCommentDao().getComments(landmark_id)
                val retrofitService = retrofit.create(CommentService::class.java)

                _commentsState.value = retrofitService.getComments(landmark_id).comments
            } catch (e: Exception) {
                _addCommentState.value = e.message.toString()
            }
        }
    }

    fun addComment(landmark_id: Int, text: String, context: Context) {
        var user: User?
        var comment: CommentDC? = null
        viewModelScope.launch {
            try {
                user = UserDataBase(context).getUserDao().getFirstUser()

                comment = user?.let {
                    CommentDC(
                        user_id = it.uuid,
                        landmark_id = landmark_id,
                        text = text,
                        created_at = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    )
                }
                comment?.let { CommentDataBase(context).getCommentDao().newComment(it) }
                comment?.let { fetchComments(it.landmark_id, context) }

            } catch (e: Exception) {
                _addCommentState.value = e.message.toString()
                e.printStackTrace()
            }
            try {
                val retrofitService = retrofit.create(CommentService::class.java)

                _addCommentState.value = comment?.let { retrofitService.postComment(it).message }.toString()
                // Optionally, you can reload the comments after adding a new one
                comment?.let { fetchComments(it.landmark_id, context) }
            } catch (e: Exception) {
                // Handle the error
                _addCommentState.value = e.message.toString()
                e.printStackTrace()
            }
        }
    }
}