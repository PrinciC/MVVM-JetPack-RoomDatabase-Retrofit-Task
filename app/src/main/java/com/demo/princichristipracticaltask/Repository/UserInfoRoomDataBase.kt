package com.mvvm.kot.Kotlin_Retrofit_Room.Repository

import UserInfoDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demo.princichristipracticaltask.Repository.PopulateDbAsync

@Database(entities = arrayOf(ResultModel::class), version = 1)
abstract  class UserInfoRoomDataBase: RoomDatabase(){
    abstract fun postInfoDao(): UserInfoDao

    companion object {
        private var INSTANCE: UserInfoRoomDataBase? = null

        fun getInstance(context: Context): UserInfoRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(UserInfoRoomDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                            UserInfoRoomDataBase::class.java, "user_info_database.db")
                            .addCallback(sRoomDataBaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        val sRoomDataBaseCallback = object:RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        };
     /* val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                InitDBAsync(INSTANCE).execute()
            }
        }*/
    }

    }
