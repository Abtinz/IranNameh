package com.android.iranname.account.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.account.viewModel.LogInViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("ShowToast")
@Composable
fun LogIn(navController: NavController) {
    val viewModel: LogInViewModel = viewModel()
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // Event handlers
    val onUsernameChanged: (String) -> Unit = { username = it }
    val onPasswordChanged: (String) -> Unit = { password = it }

//    val onPasswordVisibilityToggle: () -> Unit = {
//        // Toggle password visibility
//    }

    val onSignUpClicked: () -> Unit = {

        CoroutineScope(Dispatchers.Default).launch {
            viewModel.logIn(username, password)
            if (viewModel.logInState.value != null) {
                viewModel.newUser?.let { UserDataBase(context).getUserDao().newUser(it) }
            }
        }
    }

    // Observe viewModel.singUpResponse and handle UI accordingly

    // UI Components
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val focusManager = LocalFocusManager.current
        val interactionSource = remember { MutableInteractionSource() }
        OutlinedTextField(interactionSource = interactionSource.also { interactionSource1 ->
            LaunchedEffect(interactionSource1) {
                interactionSource1.interactions.collect {
                    if (it is PressInteraction.Cancel) {
                        focusManager.clearFocus()
                    }
                }
            }
        },
            value = username,
            onValueChange = onUsernameChanged,
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                }
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions {
                KeyboardActions.Default.onDone
                focusManager.clearFocus()
            })

        val focusManager3 = LocalFocusManager.current
        val interactionSource3 = remember { MutableInteractionSource() }
        OutlinedTextField(interactionSource = interactionSource3.also { interactionSource1 ->
            LaunchedEffect(interactionSource1) {
                interactionSource1.interactions.collect {
                    if (it is PressInteraction.Cancel) {
                        focusManager3.clearFocus()
                    }
                }
            }
        },
            value = password,
            onValueChange = onPasswordChanged,
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager3.clearFocus()
                    }
                }
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Password, contentDescription = "Password")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions {
                KeyboardActions.Default.onDone
                focusManager3.clearFocus()
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSignUpClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("LogIn")
        }
        Toast.makeText(context, viewModel.logInState.value.toString(), Toast.LENGTH_SHORT).show()


        TextButton(onClick = { navController.navigate("SignUp") }) {
            Text(text = "Don't have an account? SignUp")
        }
    }
}