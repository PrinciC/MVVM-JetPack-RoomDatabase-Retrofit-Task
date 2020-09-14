package com.demo.princichristipracticaltask.Repository

import com.google.gson.annotations.SerializedName

 class UserResponse(
	 // API Response Class
	@SerializedName("errorCode") val errorCode: Int,
	@SerializedName("errorMessage") val errorMessage: String,
	@SerializedName("user") val user: User
)