package com.demo.princichristipracticaltask.Repository

import com.google.gson.annotations.SerializedName

class User(
    // API Response Model
    @SerializedName("userId") public val userId: Int,
    @SerializedName("userName") public val userName: String
)