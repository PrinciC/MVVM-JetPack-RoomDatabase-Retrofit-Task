package com.demo.princichristipracticaltask.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.princichristipracticaltask.Utils.GlobalData
import java.util.regex.Pattern

class ValidationRepository(application: Application) {

    //check validation for user repository
    var application:Application

    init {
        this.application = application
    }

    fun validateCredentials(emailID:String,password:String): LiveData<String> {
     val loginErrorMessage = MutableLiveData<String>()
      if(isUsernameValid(emailID)){
             if(password.length<8 && !isPasswordValid(password)){
                 loginErrorMessage.value = GlobalData.LOGIN_PASSWORD_INVALID
             }else{
                 loginErrorMessage.value = GlobalData.LOGIN_SUCCESSFUL
             }
      }else{
          loginErrorMessage.value = GlobalData.LOGIN_USERNAME_INVALID
      }

        return  loginErrorMessage
    }


    fun isUsernameValid(username: String): Boolean {
        val expression = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\\\S+\$).{4,}\$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(username)
        return matcher.matches()
    }

    fun isPasswordValid(password: String): Boolean{
        val expression  ="^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\\\S+\$).{4,}\$";
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

}
