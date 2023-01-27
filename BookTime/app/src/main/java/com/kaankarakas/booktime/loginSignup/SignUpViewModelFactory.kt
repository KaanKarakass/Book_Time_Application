package com.kaankarakas.booktime.loginSignup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaankarakas.booktime.loginDatabase.UserInfoDao

class SignUpViewModelFactory(private val dao: UserInfoDao)
    :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(dao) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }
}