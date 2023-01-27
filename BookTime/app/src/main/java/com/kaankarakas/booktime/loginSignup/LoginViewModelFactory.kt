package com.kaankarakas.booktime.loginSignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaankarakas.booktime.loginDatabase.UserInfoDao

class LoginViewModelFactory(private val dao: UserInfoDao)
    :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(dao) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }
}