package com.demo.princichristipracticaltask.Repository

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIServices {

    // API Call
    @POST("api/login")
    @FormUrlEncoded
    fun loginRequest(@Field("username") username: String, @Field("password") password: String): Call<UserResponse>
}