package com.android.iranname.account.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.User
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.commonServices.network.RetrofitClient.SignUpInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _singUpResponse = MutableStateFlow("")
    val singUpResponse: StateFlow<String> get() = _singUpResponse

    private val _isUserSingedUp = MutableStateFlow(false)
    val isUserSingedUp: StateFlow<Boolean> get() = _isUserSingedUp

    //this is our user information object
    private val _newUser  = MutableStateFlow<User?>(null)
    val newUser: StateFlow<User?> get() = _newUser

    /*
     * this is our service for register api request and its response handling
     */
    fun signUpApiService(
        username: String,
        email: String,
        password: String,
        context: Context
    ) {
        viewModelScope.launch {
            try {
                val response = SignUpInstance.userSignUp(
                    username = username,
                    password1 = password,
                    email = email,
                    password2 = password
                )

                _singUpResponse.value = response.message.toString()
                _isUserSingedUp.value = response.success
                //successful register request
                if (response.success) {
                    _newUser.value = User(username, response.user_id)
                    _newUser.value?.let { UserDataBase(context).getUserDao().newUser(it) }
                    isLoggedChecked()
                }
            } catch (exception: Throwable) {
                _singUpResponse.value = exception.message.toString()
            }
        }
    }

    /*
    * this function will change _isUserSingedUp live data value to false
    * because we need to update it in future ->avoiding of conflicts
    */
    private fun isLoggedChecked() {
        _isUserSingedUp.value = false
    }
}