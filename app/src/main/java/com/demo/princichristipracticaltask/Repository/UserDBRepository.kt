package com.demo.princichristipracticaltask.Repository

import android.app.Application
import android.content.Context

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserDBRepository {
    companion object {

        var userInfoRoomDataBase: UserInfoRoomDataBase? = null

        var resultModel: LiveData<ResultModel>? = null


        fun initializeDB(context: Context): UserInfoRoomDataBase {
            return UserInfoRoomDataBase.getDataseClient(context)
        }

        fun insertData(context: Context, userName: String, password: String) {

            userInfoRoomDataBase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = ResultModel(userName, password)
                userInfoRoomDataBase!!.loginDao().insert(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String): LiveData<ResultModel>? {

            userInfoRoomDataBase = initializeDB(context)

            resultModel = userInfoRoomDataBase!!.loginDao().getLoginDetails(username)

            return resultModel
        }
    }
}