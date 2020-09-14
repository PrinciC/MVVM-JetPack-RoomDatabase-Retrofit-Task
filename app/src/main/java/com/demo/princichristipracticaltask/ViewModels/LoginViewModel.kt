package com.demo.princichristipracticaltask.ViewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.princichristipracticaltask.Repository.User
import com.demo.princichristipracticaltask.Repository.UserDBRepository
import com.demo.princichristipracticaltask.Repository.ValidationRepository

class LoginViewModel(private val context: Context, application: Application) :
    AndroidViewModel(application) {
    // LoginViewModel
    private lateinit var userRepository: UserDBRepository
    private lateinit var mAllUsers: LiveData<List<User>>
    private lateinit var validationRepository: ValidationRepository

    /* public constructor(application: Application) : super(application) {
         validationRepository = ValidationRepository(application)
         userRepository = UserDBRepository(application)
         mAllUsers = userRepository.getAllUsers()
     }*/

    // validation for user data
    fun validateCredentials(email: String, passWord: String): LiveData<String> {
        return validationRepository.validateCredentials(email, passWord)
    }

    // get all user
    fun getAllUsers(): LiveData<List<User>> {
        return mAllUsers
    }

    // insert user data
    fun insert(user: User) {
        userRepository.insert(user)
    }
}