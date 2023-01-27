package com.kaankarakas.booktime.searchBooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaankarakas.booktime.bookDatabase.BookInfo
import com.kaankarakas.booktime.bookDatabase.BookInfoDao
import kotlinx.coroutines.launch


class SearchBookViewModel(val dao:BookInfoDao, val text:String):ViewModel() {
    val enterText = text

    var search = ""

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    var books = MutableLiveData<List<BookInfo>>()

    fun getList(){
        val thread = Thread{
            val book = when(text){
                "Enter Author Name" -> dao.getByAuthor(search)
                "Enter Book Name" -> dao.getByName(search)
                else -> dao.getByCategory(search)
            }
            books.postValue(book)
        }
        thread.start()
    }
    fun getFavBooks(){
        viewModelScope.launch {
            books.postValue(dao.getFavorite(true))
        }

    }
    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
    }
}