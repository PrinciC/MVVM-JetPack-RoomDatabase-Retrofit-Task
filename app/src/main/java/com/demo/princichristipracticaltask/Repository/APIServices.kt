package com.demo.princichristipracticaltask.Repository

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface APIServices {

    @POST("api/login")
    fun loginRequest(@Field("username") username: String, @Field("password") password: String): Call<User>
}