package com.kaankarakas.booktime.loginDatabase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
abstract class UserInfoDatabase: RoomDatabase() {
    abstract val userInfoDao: UserInfoDao

    companion object{
        @Volatile
        private var INSTANCE: UserInfoDatabase? = null

        //if database doesnt already exist, getInstace method goes and builds it
        fun getInstance(context:Context): UserInfoDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserInfoDatabase::class.java,
                        "userInfo_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}