package com.android.iranname.account.ui.screen

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.account.viewModel.SignUpViewModel
import com.android.iranname.mainmenu.ui.model.homeScreenRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SignUp(navHostController: NavHostController) {
    val viewModel: SignUpViewModel = viewModel()
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val singUpResponse by viewModel.singUpResponse.collectAsState()
//    val isUserSingedUp by viewModel.isUserSingedUp.collectAsState()


    // Event handlers
    val onUsernameChanged: (String) -> Unit = { username = it }
    val onPasswordChanged: (String) -> Unit = { password = it }
    val onEmailChanged: (String) -> Unit = { email = it }

//    val onPasswordVisibilityToggle: () -> Unit = {
//        // Toggle password visibility
//    }

    val onSignUpClicked: () -> Unit = {
        if (username.isNotEmpty()) {

            CoroutineScope(Dispatchers.Default).launch {
                println("hey")
                viewModel.signUpApiService(username, email, password)
                if (singUpResponse == "Registration successful") {
                    viewModel.newUser?.let { UserDataBase(context).getUserDao().newUser(it) }
                    println("hooey")
                    viewModel.isLoggedChecked()
                }
            }
        } else {
            // Handle empty username
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
        OutlinedTextField(
            interactionSource = interactionSource.also { interactionSource1 ->
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
            }
        )

        val focusManager3 = LocalFocusManager.current
        val interactionSource3 = remember { MutableInteractionSource() }
        OutlinedTextField(
            interactionSource = interactionSource3.also { interactionSource1 ->
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
            }
        )

        val focusManager2 = LocalFocusManager.current
        val interactionSource2 = remember { MutableInteractionSource() }
        OutlinedTextField(
            interactionSource = interactionSource2.also { interactionSource1 ->
                LaunchedEffect(interactionSource1) {
                    interactionSource1.interactions.collect {
                        if (it is PressInteraction.Cancel) {
                            focusManager2.clearFocus()
                        }
                    }
                }
            },
            value = email,
            onValueChange = onEmailChanged,
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures {
                        focusManager2.clearFocus()
                    }
                }
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions {
                KeyboardActions.Default.onDone
                focusManager2.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSignUpClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
//            colors = ButtonDefaults.buttonColors(
//                Color.Blue,
//                Color.White,
//                Color.Transparent,
//                Color.Transparent
//            )
        ) {
            Text("Sign Up")
        }
        println(singUpResponse + "hello")
        if (singUpResponse == "Registration successful") {
            Toast.makeText(context, "Signed up Successfully!", Toast.LENGTH_SHORT).show()
            navHostController.navigate(homeScreenRoute)
        }

        TextButton(onClick = { navHostController.navigate("logIn") }) {
            Text(text = "Already have an account? LogIn")
        }
    }
}
