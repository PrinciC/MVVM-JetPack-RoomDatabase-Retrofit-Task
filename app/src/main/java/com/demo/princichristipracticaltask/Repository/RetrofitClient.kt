package com.demo.princichristipracticaltask.Repository

import android.util.Log
import com.demo.princichristipracticaltask.Repository.loggingInterceptor.LoggingInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val HTTP_TIMEOUT = TimeUnit.SECONDS.toMillis(120)
    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
            client.connectTimeout(
                HTTP_TIMEOUT,
                TimeUnit.MILLISECONDS
            )
            client.writeTimeout(
                HTTP_TIMEOUT,
                TimeUnit.MILLISECONDS
            )
            client.readTimeout(
                HTTP_TIMEOUT,
                TimeUnit.MILLISECONDS
            )
            client.addInterceptor(
                LoggingInterceptor.Builder()
                    .log(Log.ERROR)
                    .request("Request")
                    .response("Response")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("IMSI", "357175048449937")
                    .addHeader("IMEI", "510110406068589")
                    .build()
            )
            val okHttpClient = client.build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            Log.d("BASEURL", "BASEURL>>>$baseUrl")
            retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }
}