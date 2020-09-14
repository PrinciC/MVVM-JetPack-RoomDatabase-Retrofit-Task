package com.demo.princichristipracticaltask.Repository

import android.app.Application

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class UserDBRepository {
    private   var userDao: UserInfoDao
    private  var mAllUsers: LiveData<List<User>>

    // User database Repository class
    constructor(application: Application){
        val db = UserInfoRoomDataBase.getInstance(application)
        userDao = db!!.postInfoDao()
        mAllUsers = userDao.getAllUsers()
    }

    // get all users
    fun getAllUsers(): LiveData<List<User>> {
        return mAllUsers
    }

    // insert users
    fun insert(user: User) {
        InsertAsyncTask(userDao).execute(user)
    }
    class InsertAsyncTask internal  constructor(userDao: UserInfoDao): AsyncTask<User, Void, Void>(){
        private  var mAsyncUserDao: UserInfoDao = userDao
        override fun doInBackground(vararg p0: User): Void? {
            mAsyncUserDao.insert(p0[0])
            return null

        }
    }
}