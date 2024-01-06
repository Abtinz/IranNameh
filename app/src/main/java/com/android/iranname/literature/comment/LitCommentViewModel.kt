package com.android.iranname.literature.comment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.User
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.commonServices.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LitCommentViewModel : ViewModel() {

    private val _commentsState  = MutableStateFlow<List<LitComment>>(emptyList())
    val commentsState: StateFlow<List<LitComment>> get() = _commentsState

    private val _commentsState2  = MutableStateFlow<List<LitComment>>(emptyList())
    val commentsState2: StateFlow<List<LitComment>> get() = _commentsState2

    private val _addCommentState = MutableStateFlow("")
    val addCommentState: StateFlow<String> get() = _addCommentState



    // Launch the coroutine when needed
    fun fetchComments(literature_id: Int, context: Context) {
        viewModelScope.launch {
            try {
                //building api service with its headers and basic url and instances
                _commentsState2.value = LitDataBase(context).getLitDao().getComments(literature_id)
                val retrofitService = RetrofitClient.retrofit.create(LitCommentService::class.java)

                _commentsState.value = retrofitService.getComments(literature_id).comments
            } catch (e: Exception) {
                _addCommentState.value = e.message.toString()
            }
        }
    }

    fun addComment(literature_id: Int, text: String, context: Context) {
        var user: User?
        var comment: LitComment? = null
        viewModelScope.launch {
            try {
                user = UserDataBase(context).getUserDao().getFirstUser()

                comment = user?.let {
                    LitComment(
                        user_id = it.uuid,
                        literature_id = literature_id,
                        text = text,
                        created_at = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    )
                }
                comment?.let { LitDataBase(context).getLitDao().newComment(it) }
                comment?.let { fetchComments(it.literature_id, context) }

            } catch (e: Exception) {
                _addCommentState.value = e.message.toString()
                e.printStackTrace()
            }
            try {
                val retrofitService = RetrofitClient.retrofit.create(LitCommentService::class.java)

                _addCommentState.value = comment?.let { retrofitService.postComment(it).message }.toString()
                // Optionally, you can reload the comments after adding a new one
                comment?.let { fetchComments(it.literature_id, context) }
            } catch (e: Exception) {
                // Handle the error
                _addCommentState.value = e.message.toString()
                e.printStackTrace()
            }
        }
    }
}