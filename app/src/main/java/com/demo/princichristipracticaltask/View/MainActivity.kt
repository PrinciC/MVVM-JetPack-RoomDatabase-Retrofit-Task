package com.demo.princichristipracticaltask.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.princichristipracticaltask.R
import com.demo.princichristipracticaltask.Repository.APIURL.Companion.apiService
import com.demo.princichristipracticaltask.Repository.User
import com.demo.princichristipracticaltask.Utils.GlobalData
import com.demo.princichristipracticaltask.ViewModels.LoginViewModel
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // LoginViewModel for observing login data
    lateinit var loginViewModel: LoginViewModel

    lateinit var loginUsername: EditText
    lateinit var loginPassword: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // initialize data
        initData()

    }

    private fun initData() {
        loginUsername = findViewById<EditText>(R.id.edtUsername)
        loginPassword = findViewById<EditText>(R.id.edtPassword)
        loginBtn = findViewById<Button>(R.id.btnLogin)
        loginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Login button click event
        if (v == loginBtn) {
            // Check validation for username & password
            checkValidate()
        }

    }

    private fun checkValidate() {

        val username: String = loginUsername.text.toString()
        val password: String = loginPassword.text.toString()

        if (username.isEmpty()) {
            loginUsername.error = GlobalData.USERNAME_MESSAGE
            return
        }

        if (username.length > GlobalData.USERNAME_LENGTH) {
            loginUsername.error = GlobalData.LOGIN_USERNAME_VALIDATION_MESSAGE
            return
        }

        if (password.isEmpty()) {
            loginPassword.error = GlobalData.PASSWORD_MESSAGE
            return
        }

        if (password.length > GlobalData.PASSWORD_LENGTH) {
            loginPassword.error = GlobalData.LOGIN_PASSWORD_VALIDATION_MESSAGE
            return
        }

        if (password.length < 8) {
            loginPassword.error = GlobalData.LOGIN_PASSWORD_INVALID
            return
        }

        // Login api call
        try {
            loginViewModel = ViewModelProviders.of(this!!).get(LoginViewModel::class.java)
            loginViewModel.validateCredentials(username, password).observe(this, object :
                Observer<String> {
                override fun onChanged(t: String?) {
                    callLoginRequest(username, password)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun callLoginRequest(username: String, password: String) {

        try {
            val apiService = apiService
            //Defining retrofit api service
            apiService.loginRequest(username, password).enqueue(object : Callback<User> {
                // API Success
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    Log.d("Repository", "Response::::" + response.body()!!)

                    // insert api response user data in database
                    loginViewModel.insert(response.body()!!)

                    //Go to UserDetail Activity
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this@MainActivity, UserDetailActivity::class.java);
                        var userName = username
                        var password = password
                        intent.putExtra(GlobalData.USERNAME, userName)
                        intent.putExtra(GlobalData.PASSWORD, password)
                        startActivity(intent);
                    }, 2000)
                }

                // API Failure
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("Repository", "Failed:::")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

