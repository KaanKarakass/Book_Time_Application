package com.kaankarakas.booktime.searchBooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaankarakas.booktime.bookDatabase.BookInfoDao


class SearchBookViewModelFactory(private val dao: BookInfoDao,private val text:String)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchBookViewModel::class.java)){
            return SearchBookViewModel(dao,text) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }
}