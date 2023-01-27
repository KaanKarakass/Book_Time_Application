package com.kaankarakas.booktime.editBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaankarakas.booktime.bookDatabase.BookInfoDao

class EditBookViewModelFactory(private val bookId:Long,
                               private val dao:BookInfoDao)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditBookViewModel::class.java)){
            return EditBookViewModel(bookId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}