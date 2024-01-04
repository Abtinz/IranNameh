package com.android.iranname.account.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.User
import com.android.iranname.account.network.LogInApiService
import com.android.iranname.commonServices.network.RetrofitClient.retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LogInViewModel : ViewModel() {


    private val _logInState = MutableStateFlow(false)
    val logInState: StateFlow<Boolean> get() = _logInState


    var newUser: User? = null

    fun logIn(username: String, password: String) {
        viewModelScope.launch {
            try {

                val response = retrofit.create(LogInApiService::class.java)
                    .userLogIn(username = username, password = password)

                newUser = User(username, response.user_id)
                _logInState.value = response.success

            } catch (exception: HttpException) {
                _logInState.value = false
            }
        }
    }
}
