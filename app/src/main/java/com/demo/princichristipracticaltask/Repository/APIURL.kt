package com.demo.princichristipracticaltask.Repository

import com.demo.princichristipracticaltask.Repository.RetrofitClient.getClient

public class APIURL {

    // API Base Url
    companion object {
        const val BASE_URL = "http://private-222d3-homework5.apiary-mock.com/"
        val apiService: APIServices
            get() = getClient(BASE_URL)!!.create(APIServices::class.java)
    }
}