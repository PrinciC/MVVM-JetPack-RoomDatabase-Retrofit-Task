package com.demo.princichristipracticaltask.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.princichristipracticaltask.R
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        setData()
    }

    private fun setData() {
        var strUser: String = intent.getStringExtra("Username")
        var strPassword: String = intent.getStringExtra("Password")

        tvLoginData.setText(getString(R.string.str_successful_login) +" "+strUser)
    }
}