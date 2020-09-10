package com.demo.princichristipracticaltask.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.princichristipracticaltask.R
import com.demo.princichristipracticaltask.Repository.APIURL
import com.demo.princichristipracticaltask.Repository.APIServices
import com.demo.princichristipracticaltask.ViewModels.LoginViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var loginViewModel: LoginViewModel

    lateinit var loginUsername: EditText
    lateinit var loginPassword: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProviders.of(this!!).get(LoginViewModel::class.java)

        loginUsername = findViewById<EditText>(R.id.edtUsername)
        loginPassword = findViewById<EditText>(R.id.edtPassword)
        loginBtn = findViewById<Button>(R.id.btnLogin)
        loginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v == loginBtn) {
            checkValidate()
        }

    }

    private fun checkValidate() {

        val username: String = loginUsername.text.toString()
        val password: String = loginPassword.text.toString()


        if (username.isEmpty()) {
            loginUsername.error = "Enter Username"
            return
        }

        if (username.length > 30) {
            loginUsername.error = "Username should be less then 30 character"
            return
        }

        if (password.isEmpty()) {
            loginPassword.error = "Enter Password"
            return
        }

        if (password.length > 16) {
            loginPassword.error = "Enter should be less then 16 character"
            return
        }

        if (password.length < 8) {
            loginPassword.error = "Invalid Password"
            return
        }

        Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show()
        // api call
        loginViewModel.validateCredentials(username,password).observe(this,object:
            Observer<String> {
            override fun onChanged(t: String?) {
                callLoginRequest(username, password)
            }
        })

    }

    private fun callLoginRequest(username: String, password: String) {

        fun providesOkHttpClientBuilder(): OkHttpClient {

            val httpClient = OkHttpClient.Builder()
            return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build()
        }

        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(APIURL.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(providesOkHttpClientBuilder())
                .build()

            //Defining retrofit api service
            val service = retrofit.create(APIServices::class.java)
            service.loginRequest(username, password).enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    Log.d("Repository", "Response::::" + response.body()!!)
                    loginViewModel.insert(response.body())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Repository", "Failed:::")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

