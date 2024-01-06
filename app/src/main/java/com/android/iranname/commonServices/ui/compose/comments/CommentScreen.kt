package com.android.iranname.commonServices.ui.compose.comments

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.iranname.account.viewModel.LogInViewModel
import com.android.iranname.commonServices.model.CommentDC
import com.android.iranname.commonServices.viewModel.CommentViewModel
import com.android.iranname.landmarks.ui.theme.informationText
import com.android.iranname.landmarks.ui.theme.primary
import com.android.iranname.landmarks.ui.theme.secondary
import com.android.iranname.landmarks.ui.theme.tertiary
import com.android.iranname.literature.ui.theme.DarkBlur
import com.android.iranname.literature.ui.theme.LightBlue
import com.android.iranname.literature.ui.theme.Pink40

@Composable
fun CommentScreen(landmark_id: Int) {
    val context = LocalContext.current
    val viewModel : CommentViewModel = viewModel()
    viewModel.fetchComments(landmark_id, context)

    val comments by viewModel.commentsState.collectAsState()
    val comments2 by viewModel.commentsState2.collectAsState()

    Column {
        Text(
            text = "Comments \n",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp),
            color = informationText
        )
        if (comments.isNotEmpty()) {
            comments.forEach { comment ->
                CommentItem(comment)
            }
        }else{
            comments2.forEach { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: CommentDC) {
    val context = LocalContext.current
    val viewModel: LogInViewModel = viewModel()
    viewModel.loadUser(context)
    val loadedUser by viewModel.loadedUser.collectAsState()
    Card(
        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        ),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
//            .border(2.dp, Pink40, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(10.dp))
//            .background(DarkBlur)
            .fillMaxWidth()
    ) {
        loadedUser?.let {
            Text(
                text = "UserName: "+it.user_id,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp),
                color = secondary
            )
        }
        Text(text = comment.text, modifier = Modifier.padding(5.dp), color = Color.Black)
    }
}

@Composable
fun AddComment(landmark_id: Int) {
    val context = LocalContext.current
    val viewModel : CommentViewModel = viewModel()

    val addCommentState by viewModel.addCommentState.collectAsState()
    if (addCommentState == "Comment saved"){
        Toast.makeText(context, addCommentState, Toast.LENGTH_SHORT).show()
    }

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
                    // Post the new comment
                    viewModel.addComment(
                        landmark_id = landmark_id,
                        text = newCommentState.value,
                        context = context
                    )

                    // Clear the input field
                    newCommentState.value = ""

                    // Hide the keyboard
                    keyboardController?.hide()
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = tertiary,
                unfocusedContainerColor = primary,
                focusedTextColor = secondary
            )
        )

        Button(
            onClick = {
                if (newCommentState.value.isNotEmpty()) {
                    // Post the new comment

                    viewModel.addComment(
                        landmark_id = landmark_id,
                        text = newCommentState.value,
                        context = context
                    )


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