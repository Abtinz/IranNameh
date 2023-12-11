package com.android.iranname.account.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.User
import com.android.iranname.commonServices.network.RetrofitClient.SignUpInstance
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _singUpResponse = MutableLiveData<String>()
    val singUpResponse: LiveData<String>
        get() = _singUpResponse

    private val _isUserSingedUp = MutableLiveData<Boolean>()
    val isUserSingedUp: LiveData<Boolean>
        get() = _isUserSingedUp

    //this is our user information object
    var newUser: User? = null

    init {
        _singUpResponse.value = "initial"
        _isUserSingedUp.value = false
    }

    /*
     * this is our service for register api request and its response handling
     */
    suspend fun signUpApiService(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = SignUpInstance.userSignUp(username = username, password1 = password, email = email, password2 = password)
                println(response)
                _singUpResponse.value = response.success.toString()
                //successful register request
                if (_singUpResponse.value == "true") {
                    newUser = User(response.user_id)
                    _isUserSingedUp.value = true
                }
            } catch (exception: Throwable) {
                _singUpResponse.value = exception.message
            }
        }
    }

    /*
    * this function will change _isUserSingedUp live data value to false
    * because we need to update it in future ->avoiding of conflicts
    */
    fun isLoggedChecked() {
        _isUserSingedUp.value = false
    }
}