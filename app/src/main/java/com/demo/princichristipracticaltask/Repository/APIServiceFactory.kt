package com.demo.princichristipracticaltask.Repository

import UserResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.ArrayList
import java.util.concurrent.TimeUnit

public class APIServiceFactory {
    public fun providesOkHttpClientBuilder(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS).build()

    }

    /*fun providesWebService(): LiveData<List<UserResponse>> {
        val data = MutableLiveData<List<UserResponse>>()
        var webserviceResponseList: List<UserResponse>



        return data

    }

   *//* private fun parseJson(response: String?): List<UserResponse> {

        val apiResults = ArrayList<ResultModel>()

        val jsonObject: JSONObject

        val jsonArray: JSONArray

        try {
            jsonArray = JSONArray(response)

            for (i in 0 until jsonArray.length()) {
                var jsonInfo:JSONObject = jsonArray.getJSONObject(i)

                val mMovieModel = ResultModel()
                //mMovieModel.setId(object.getString("id"));
                mMovieModel.setId(Integer.parseInt(jsonInfo.getString("id")))
                mMovieModel.setTitle(jsonInfo.getString("title"))
                mMovieModel.setBody(jsonInfo.getString("body"))

                apiResults.add(mMovieModel)
            }


        } catch (e: JSONException) {
            e.printStackTrace()
        }

        Log.i(javaClass.simpleName, apiResults.size.toString())
        return apiResults

    }*//**//*
*/

}