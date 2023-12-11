package com.android.iranname.commonServices.ui.compose.comments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.commonServices.model.CommentDC
import com.android.iranname.commonServices.viewModel.CommentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CommentScreen(landmark_id: Int) {
    val commentViewModel = CommentViewModel()
    LaunchedEffect(true) {
        commentViewModel.fetchComments(landmark_id)
    }

    val comments = remember(commentViewModel.commentsState.value) {
        commentViewModel.commentsState.value
    }

    Column {
        comments.forEach { comment ->
            CommentItem(comment)
        }
    }
}

@Composable
fun CommentItem(comment: CommentDC) {
    Text(text = comment.text)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddComment(landmark_id: Int) {
    val context = LocalContext.current
    val commentViewModel = CommentViewModel()

    // New comment input state
    val newCommentState = remember { mutableStateOf("") }

    // Get the keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current

    // UI for adding comments
    Column {
        TextField(
            value = newCommentState.value,
            onValueChange = { newCommentState.value = it },
            label = { Text("Add a comment") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions {
                if (newCommentState.value.isNotEmpty()) {
                    var uuid = ""
                    // Post the new comment
                    CoroutineScope(Dispatchers.Default).launch {
                        uuid = UserDataBase(context).getUserDao().getFirstUser()?.uuid.toString()
                    }
                    commentViewModel.addComment(
                        CommentDC(
                            user_id = uuid,
                            landmark_id = landmark_id,
                            text = newCommentState.value,
                            created_at = LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        ),
                        landmark_id
                    )

                    // Clear the input field
                    newCommentState.value = ""

                    // Hide the keyboard
                    keyboardController?.hide()
                }
            }
        )

        Button(
            onClick = {
                if (newCommentState.value.isNotEmpty()) {
                    // Post the new comment
                    CoroutineScope(Dispatchers.Default).launch {
                        UserDataBase(context).getUserDao().getFirstUser()?.let {
                            CommentDC(
                                user_id = it.uuid,
                                landmark_id = landmark_id,
                                text = newCommentState.value,
                                created_at = LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            )
                        }?.let {
                            commentViewModel.addComment(
                                it,
                                landmark_id
                            )
                        }
                    }

                    // Clear the input field
                    newCommentState.value = ""

                    // Hide the keyboard
                    keyboardController?.hide()
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Send")
        }
    }
}