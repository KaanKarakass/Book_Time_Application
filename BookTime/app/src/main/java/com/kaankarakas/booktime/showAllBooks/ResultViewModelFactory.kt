package com.kaankarakas.booktime.showAllBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaankarakas.booktime.bookDatabase.BookInfoDao

class ResultViewModelFactory(private val dao: BookInfoDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(dao) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }
}