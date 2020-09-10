package com.demo.princichristipracticaltask.Repository

import User
import UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIServices {
    @Headers("Content-Type:application/json", "IMSI:357175048449937", "IMEI:510110406068589")
    @POST("api/login")
    fun loginRequest(@Field("username") username: String, @Field("password") password: String): Call<String>
}