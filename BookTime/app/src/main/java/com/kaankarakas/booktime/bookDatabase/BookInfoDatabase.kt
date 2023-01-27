package com.kaankarakas.booktime.bookDatabase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookInfo::class], version = 1, exportSchema = false)
abstract class BookInfoDatabase: RoomDatabase() {
    abstract val bookInfoDao: BookInfoDao

    companion object{
        @Volatile
        private var INSTANCE: BookInfoDatabase? = null

        //if database doesnt already exist, getInstace method goes and builds it
        fun getInstance(context:Context): BookInfoDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookInfoDatabase::class.java,
                        "bookInfo_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}