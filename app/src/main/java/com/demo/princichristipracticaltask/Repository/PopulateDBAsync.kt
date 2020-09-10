package com.demo.princichristipracticaltask.Repository

import UserInfoDao
import android.os.AsyncTask
import com.mvvm.kot.Kotlin_Retrofit_Room.Repository.UserInfoRoomDataBase

    class PopulateDbAsync internal constructor(db: UserInfoRoomDataBase) : AsyncTask<Void, Void, Void>() {

        private val mDao: UserInfoDao

        init {
            mDao = db.postInfoDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deleteAll()
            return null
        }
    }
