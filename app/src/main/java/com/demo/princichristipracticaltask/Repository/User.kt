import com.google.gson.annotations.SerializedName


data class User(

    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String
)