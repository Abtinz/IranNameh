package com.android.iranname.setting.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import com.android.iranname.account.ui.theme.DarkGreen
import com.android.iranname.commonServices.network.quality.UserNetworkChecker
import com.android.iranname.commonServices.ui.compose.error.network.NetworkIsGoneScreen
import com.android.iranname.setting.SettingsViewModel

@Composable
fun SettingHelpScreen() {
    val context = LocalContext.current

    val onQuestionAsked = remember {
        mutableStateOf(false)
    }

    val isInternetAvailable = remember {
        mutableStateOf(UserNetworkChecker(context).checkNetwork)
    }

    if (isInternetAvailable.value) {
        LazyColumn {
            item {
                AskQuestion(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onQuestionSubmitted = { question ->
                        onQuestionAsked.value = onQuestionSubmitted(
                            question = question,
                            context = context
                        )
                    }
                )
                if (onQuestionAsked.value) {
                    SuccessfulQuestion(onQuestionAsked)
                    onQuestionAsked.value = false
                }
            }
        }
    } else {
        NetworkIsGoneScreen {
            isInternetAvailable.value = true
        }
    }
}

@Composable
fun SuccessfulQuestion(onQuestionAsked: MutableState<Boolean>) {
    if (onQuestionAsked.value) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.LightGray)
                .clip(RoundedCornerShape(10.dp))
                .shadow(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(16.dp),
            ) {
                Text(
                    text = "Question asked successfully.\n We will answer you as soon as possible.\n Thank you.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onQuestionAsked.value = false },
                    modifier = Modifier
                        .background(Color.Transparent)
                        .wrapContentSize()
                ) {
                    Icon(
                        Icons.Default.Close,
                        "close icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .background(Color.Transparent)
                    )
                }
            }
        }
    }
}

@Composable
fun AskQuestion(
    modifier: Modifier = Modifier,
    onQuestionSubmitted: (String) -> Unit
) {
    var question by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile image or icon can be added here
            Icon(
                imageVector = Icons.Default.QuestionAnswer,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )

            TextField(
                value = question,
                onValueChange = { question = it },
                placeholder = { Text("Ask a question?") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = DarkGreen,
                    backgroundColor = Color.LightGray,
                    focusedIndicatorColor = DarkGreen,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
                    .clickable {
                        if (question.isNotBlank()) {
                            onQuestionSubmitted(question)
                            question = ""
                        }
                    }
            )
        }
    }
}


fun onQuestionSubmitted(
    question: String,
    viewModel: SettingsViewModel = SettingsViewModel(),
    context: Context
): Boolean {
    viewModel.askQuestion(question = question)
    when (viewModel.helpApiStatus.asLiveData().value!!) {
        "our team will respond you later"-> Toast.makeText(context, "Our team will respond to you later.", Toast.LENGTH_SHORT).show()
        "server time out" -> {
            Toast.makeText(context, "Server time out. Please try again later.", Toast.LENGTH_SHORT).show()
        }

        "some things went wrong" -> {
            Toast.makeText(context, "Somethings went wrong. Please try again later.", Toast.LENGTH_SHORT).show()
        }

        else -> {
            println(viewModel.helpApiStatus.asLiveData())
            return true
        }
    }
    return false
}