package com.kaankarakas.booktime.loginDatabase
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kaankarakas.booktime.loginDatabase.UserInfo

@Dao
interface UserInfoDao {
    @Insert
    suspend fun insert(userInfo: UserInfo)

    @Delete
    suspend fun delete(userInfo: UserInfo)

    @Query("SELECT * FROM user_table WHERE userId=:userId")
    fun get(userId:Long):LiveData<UserInfo>

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE user_mail=:userMail AND user_password=:userPassword)")
    fun login(userMail:String, userPassword:String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE user_mail=:userMail)")
    fun signUp(userMail:String): Boolean

}