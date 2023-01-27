package com.kaankarakas.booktime.loginDatabase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_table")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "user_mail")
    var userMail: String = "",

    @ColumnInfo(name = "user_password")
    var userPsw: String = ""
)

