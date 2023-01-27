package com.kaankarakas.booktime.bookDatabase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "book_table")
data class BookInfo(
    @PrimaryKey(autoGenerate = true)
    var bookId: Long = 0L,

    @ColumnInfo(name = "book_name")
    var bookName: String = "",

    @ColumnInfo(name = "book_author")
    var bookAuthor: String = "",

    @ColumnInfo(name = "book_category")
    var bookCategory: String = "",

    @ColumnInfo(name = "book_favorite")
    var favorite: Boolean = false
)