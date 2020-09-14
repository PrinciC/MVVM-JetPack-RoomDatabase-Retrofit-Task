package com.demo.princichristipracticaltask.View

import UserResponse
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.princichristipracticaltask.R
import com.demo.princichristipracticaltask.Repository.APIURL
import com.demo.princichristipracticaltask.Utils.AppUtils
import com.demo.princichristipracticaltask.Utils.AppUtils.Companion.showToast
import com.demo.princichristipracticaltask.Utils.GlobalData
import com.demo.princichristipracticaltask.ViewModels.LoginViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // LoginViewModel for observing login data
    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context

    lateinit var loginUsername: EditText
    lateinit var loginPassword: EditText
    lateinit var loginBtn: Button
    lateinit var progessdialog: ProgressBar

    val apiService = APIURL.apiService

    val username: String = loginUsername.text.toString()
    val password: String = loginPassword.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // initialize data
        initData()

    }

    private fun initData() {
        try {
            loginUsername = findViewById<EditText>(R.id.edtUsername)
            loginPassword = findViewById<EditText>(R.id.edtPassword)
            loginBtn = findViewById<Button>(R.id.btnLogin)
            progessdialog = findViewById<ProgressBar>(R.id.progessdialog)
            loginBtn.setOnClickListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onClick(v: View?) {
        // Login button click event
        if (v == loginBtn) {
            // Check validation for username & password
            checkValidate()
        }
    }

    private fun checkValidate() {

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

        if (password.length < GlobalData.PASSWORD_LENGTH_LESS) {
            loginPassword.error = GlobalData.LOGIN_PASSWORD_INVALID
            return
        }

        // Login api call
        try {

            //check internet connection
            if (isNetworkConnected(this@MainActivity)) {

                //call Viewmodel
                context = this@MainActivity
                loginViewModel =
                    ViewModelProvider(this, MyViewModelFactory(this.application, context)).get(
                        LoginViewModel::class.java
                    )

                callLoginRequest(username, password)

            } else {
                showToast(this, getString(R.string.internet_check))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun callLoginRequest(username: String, password: String) {

        try {
            //progressdialog
            progessdialog.visibility = View.VISIBLE


            //Defining retrofit api service
            apiService.loginRequest(username, password).enqueue(object : Callback<UserResponse> {
                // API Success
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    Log.d("Repository", "Response::::" + response.body()!!)

                    val responce: UserResponse? = response.body()

                    if (responce == null) {
                        val responseBody = response.errorBody()
                        if (responseBody != null) {
                            try {

                                showToast(this@MainActivity,GlobalData.Unknown)

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    } else {

                        progessdialog.visibility = View.GONE

                        //200 sucess
                        Log.d("TAG", "===Response==" + response.body().toString())
                        Log.d("OP_: ", Gson().toJson(response.body()))

                        if (responce.errorMessage.equals(GlobalData.APISUCCESS)) {

                            //Go to UserDetail Activity
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent =
                                    Intent(this@MainActivity, UserDetailActivity::class.java)
                                intent.putExtra(GlobalData.USERNAME, username)
                                intent.putExtra(GlobalData.PASSWORD, password)
                                startActivity(intent);
                            }, 2000)

                            // insert api response user data in database
                            loginViewModel.insert(responce.user)

                        } else {

                            showToast(this@MainActivity,GlobalData.Unknown)

                        }

                    }

                }

                // API Failure
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Repository", "Failed:::")
                    progessdialog.visibility = View.GONE
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            progessdialog.visibility = View.GONE
        }

    }

    class MyViewModelFactory(private val mApplication: Application, private val context: Context) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(context, mApplication) as T
        }
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}

