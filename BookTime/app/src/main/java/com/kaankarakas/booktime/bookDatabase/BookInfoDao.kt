package com.kaankarakas.booktime.bookDatabase
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookInfoDao {
    @Insert
    suspend fun insert(bookInfo: BookInfo)

    @Delete
    suspend fun delete(bookInfo: BookInfo)

    @Update
    suspend fun update(bookInfo: BookInfo)

    @Query("SELECT * FROM book_table WHERE bookId=:bookId")
    fun get(bookId:Long):LiveData<BookInfo>


    @Query("SELECT COUNT(*) FROM book_table")
    fun check(): Int

    @Query("SELECT * FROM book_table ORDER BY bookId DESC")
    fun getAll(): LiveData<List<BookInfo>>

    @Query("SELECT * FROM book_table WHERE book_name=:bookName")
    fun getByName(bookName: String): List<BookInfo>

    @Query("SELECT * FROM book_table WHERE book_author=:bookAuthor")
    fun getByAuthor(bookAuthor: String): List<BookInfo>

    @Query("SELECT * FROM book_table WHERE book_category=:category")
    fun getByCategory(category: String): List<BookInfo>

    @Query("SELECT * FROM book_table WHERE book_favorite=:tmp")
    suspend fun getFavorite(tmp:Boolean): List<BookInfo>

}