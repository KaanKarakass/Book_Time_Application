package com.kaankarakas.booktime.editBooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaankarakas.booktime.bookDatabase.BookInfoDao
import kotlinx.coroutines.launch

class EditBookViewModel(bookId:Long, val dao:BookInfoDao):ViewModel() {
    val book = dao.get(bookId)

    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun updateBook(){
        viewModelScope.launch {
            dao.update(book.value!!)
            _navigateToList.value =true
        }
    }
    fun deleteBook(){
        viewModelScope.launch {
            dao.delete(book.value!!)
            _navigateToList.value = true
        }
    }
    fun onNavigatedToList(){
        _navigateToList.value = false
    }
    fun addFavorite(){
        book.value!!.favorite = true
        viewModelScope.launch {
            dao.update(book.value!!)
            _navigateToList.value =true
        }
    }
    fun deleteFavorite(){
        book.value!!.favorite = false
        viewModelScope.launch {
            dao.update(book.value!!)
            _navigateToList.value =true
        }
    }
}