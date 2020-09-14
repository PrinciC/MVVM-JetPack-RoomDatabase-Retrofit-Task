package com.demo.princichristipracticaltask.Repository

import android.os.AsyncTask

    //Create asynctask for database
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
