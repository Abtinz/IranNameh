package com.android.iranname.account.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.account.db.User
import com.android.iranname.account.db.UserDataBase
import com.android.iranname.account.network.LogInApiService
import com.android.iranname.commonServices.network.RetrofitClient.retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LogInViewModel : ViewModel() {


    private val _logInState = MutableStateFlow("")
    val logInState: StateFlow<String> get() = _logInState

    private val _loadedUser  = MutableStateFlow<User?>(null)
    val loadedUser: StateFlow<User?> get() = _loadedUser

    private val _newUser  = MutableStateFlow<User?>(null)
    val newUser: StateFlow<User?> get() = _newUser


    fun logIn(username: String, password: String, context: Context) {
        viewModelScope.launch {
            try {

                val response = retrofit.create(LogInApiService::class.java)
                    .userLogIn(username = username, password = password)
                _logInState.value = response.message.toString()

                if (response.success) {
                    _newUser.value = User(username, response.user_id)
                    _newUser.value?.let { UserDataBase(context).getUserDao().newUser(it) }
                }


            } catch (exception: Throwable) {
                _logInState.value = exception.message.toString()
            }
        }
    }
    fun loadUser(context: Context) {
        viewModelScope.launch {
            try {
                _loadedUser.value = UserDataBase(context).getUserDao().getFirstUser()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}