package com.demo.princichristipracticaltask.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.princichristipracticaltask.Repository.User
import com.demo.princichristipracticaltask.Repository.UserDBRepository
import com.demo.princichristipracticaltask.Repository.ValidationRepository

class LoginViewModel: AndroidViewModel {
private var validationRepository: ValidationRepository
    private lateinit var userRepository: UserDBRepository
    private lateinit var mAllUsers: LiveData<List<User>>

    constructor(application: Application) : super(application){
        validationRepository = ValidationRepository(application)
        userRepository = UserDBRepository(application)
        mAllUsers  = userRepository.getAllUsers()
    }

    fun validateCredentials(email:String,passWord:String): LiveData<String> {
        return validationRepository.validateCredentials(email,passWord)
    }

    fun getAllUsers(): LiveData<List<User>> {
        return mAllUsers
    }

    fun insert(user: User) {
        userRepository.insert(user)
    }
}