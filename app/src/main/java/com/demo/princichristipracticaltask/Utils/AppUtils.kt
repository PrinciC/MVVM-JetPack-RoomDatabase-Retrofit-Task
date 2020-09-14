package com.demo.princichristipracticaltask.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.demo.princichristipracticaltask.BuildConfig
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class AppUtils {
    companion object {
    //checking network connection
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isConnectingToInternet(context: Context): Boolean {
            try {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val list: MutableList<NetworkInfo> =
                    ArrayList()
                val networks = connectivityManager.allNetworks
                if (networks.size > 0) {
                    var info: NetworkInfo
                    for (network in networks) {
                        info = connectivityManager.getNetworkInfo(network)!!
                        if (info.state == NetworkInfo.State.CONNECTED) {
                            list.add(info)
                            return true
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return false
        }

        fun showToast(context: Context?, message: String?) {
            try {
                if (context == null) {
                    Log.d("showToast",toString())
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG && context == null) {
                    error("Assertion failed")
                }
                e.printStackTrace()
            }
        }

         fun getJsonObjFromStr(test: Any): Any? {
            var o: Any? = null
            try {
                o = JSONObject(test.toString())
            } catch (ex: JSONException) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        o = JSONArray(test)
                    }
                } catch (ex1: JSONException) {
                    return null
                }
            }
            return o
        }



    }
}