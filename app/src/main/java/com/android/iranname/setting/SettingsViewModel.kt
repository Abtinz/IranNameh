package com.android.iranname.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.iranname.commonServices.network.RetrofitClient
import com.android.iranname.setting.network.SettingsApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _helpApiStatus = MutableStateFlow("")
    val helpApiStatus: StateFlow<String> get() = _helpApiStatus

    private val _aboutApiStatus = MutableStateFlow("")
    val aboutApiStatus: StateFlow<String> get() = _aboutApiStatus

    fun askQuestion(question: String) {
        viewModelScope.launch {
            viewModelScope.launch {
                try {
                    val response = RetrofitClient.retrofit.create(SettingsApiService::class.java).help(question)
                    _helpApiStatus.value = response.message
                } catch (exception: Exception) {
                    _helpApiStatus.value = exception.message.toString()
                }
            }
        }
    }
    fun aboutUs() {
        viewModelScope.launch {
            viewModelScope.launch {
                try {
                    val response = RetrofitClient.retrofit.create(SettingsApiService::class.java).aboutUs()
                    _aboutApiStatus.value = response.message
                } catch (exception: Exception) {
                    _aboutApiStatus.value = exception.message.toString()
                }
            }
        }
    }
}