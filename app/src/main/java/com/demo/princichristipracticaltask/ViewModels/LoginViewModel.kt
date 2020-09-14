package com.demo.princichristipracticaltask.ViewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.princichristipracticaltask.Repository.ResultModel
import com.demo.princichristipracticaltask.Repository.User
import com.demo.princichristipracticaltask.Repository.UserDBRepository

class LoginViewModel(private val context: Context, application: Application) :
    AndroidViewModel(application) {
    // LoginViewModel
    var liveDataLogin: LiveData<ResultModel>? = null

    // insert data
    fun insertData(context: Context, userName: String, password: String) {
        UserDBRepository.insertData(context, userName, password)
    }

    // get all data
    fun getLoginDetails(context: Context, username: String): LiveData<ResultModel>? {
        liveDataLogin = UserDBRepository.getLoginDetails(context, username)
        return liveDataLogin
    }
}