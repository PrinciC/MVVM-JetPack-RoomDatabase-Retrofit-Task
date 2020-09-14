import com.demo.princichristipracticaltask.Repository.User
import com.google.gson.annotations.SerializedName

 class UserResponse(
	 // API Response Class
	@SerializedName("errorCode") val errorCode: Int,
	@SerializedName("errorMessage") val errorMessage: String,
	@SerializedName("user") val user: User
)