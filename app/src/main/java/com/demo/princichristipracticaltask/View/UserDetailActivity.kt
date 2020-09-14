package com.demo.princichristipracticaltask.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.princichristipracticaltask.R
import com.demo.princichristipracticaltask.Repository.ResultModel
import com.demo.princichristipracticaltask.Utils.GlobalData
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    val userDatabase : ResultModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // set User Data
        setData()
    }

    private fun setData() {
        //get data from mainactivity
        var strUser: String = intent.getStringExtra(GlobalData.USERNAME)
        var strPassword: String = intent.getStringExtra(GlobalData.PASSWORD)

        //get username fro database
        var dbUserName = userDatabase?.getBody()

        tvLoginData.setText(getString(R.string.str_successful_login) +" "+strUser)
    }
}