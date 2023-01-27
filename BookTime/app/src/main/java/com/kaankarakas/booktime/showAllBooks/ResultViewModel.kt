package com.kaankarakas.booktime.showAllBooks

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaankarakas.booktime.bookDatabase.BookInfo
import com.kaankarakas.booktime.bookDatabase.BookInfoDao
import kotlinx.coroutines.launch

class ResultViewModel(val dao:BookInfoDao):ViewModel() {

    val books = dao.getAll()

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
    }

    fun insertBooks(books: List<BookInfo>){
        //I used thread because it is necassary for dao.check()
        val thread = Thread{
            val check = dao.check()
            val handler = Handler(Looper.getMainLooper())
            handler.post{
                if(check == 0){
                    viewModelScope.launch{
                        for (book in books){
                            dao.insert(book)
                        }
                    }
                }
            }
        }
        thread.start()
    }
}