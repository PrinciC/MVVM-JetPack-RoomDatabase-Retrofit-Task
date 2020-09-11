package com.demo.princichristipracticaltask.Repository.loggingInterceptor

internal object I {
    fun Log(type: Int, tag: String?, msg: String?) {
        when (type) {
            android.util.Log.VERBOSE -> {
            }
            android.util.Log.DEBUG -> {
            }
            android.util.Log.ERROR -> {
            }
            android.util.Log.INFO -> {
            }
            android.util.Log.WARN -> {
            }
            android.util.Log.ASSERT -> {
            }
        }
    }
}