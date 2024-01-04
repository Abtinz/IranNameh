package com.android.iranname.commonServices.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.commonServices.model.CommentDC
import com.android.iranname.commonServices.network.RetrofitClient.retrofit
import com.android.iranname.commonServices.network.url.CommentService
import kotlinx.coroutines.launch

class CommentViewModel : ViewModel() {

    val commentsState = mutableStateOf<List<CommentDC>>(emptyList())
    private val errorState = mutableStateOf<String?>(null)
    private val addCommentState = mutableStateOf<String?>(null)

    private suspend fun getComments(landmark_id: Int) {
        try{
            //building api service with its headers and basic url and instances
            val retrofitService = retrofit.create(CommentService::class.java)

            commentsState.value = retrofitService.getComments(landmark_id).comments
        } catch (e: Exception) {
            errorState.value = e.message
        }
    }

    // Launch the coroutine when needed
    fun fetchComments(landmark_id: Int) {
        viewModelScope.launch {
            getComments(landmark_id)
        }
    }

    fun addComment(comment: CommentDC) {
        viewModelScope.launch {
            try {
                val retrofitService = retrofit.create(CommentService::class.java)

                addCommentState.value = retrofitService.postComment(comment).message
                // Optionally, you can reload the comments after adding a new one
                getComments(comment.landmark_id)
            } catch (e: Exception) {
                // Handle the error
                errorState.value = e.message
            }
        }
    }
}