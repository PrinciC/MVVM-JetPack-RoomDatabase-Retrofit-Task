package com.demo.princichristipracticaltask.Repository


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_info")
class ResultModel(
    //database table create
    @ColumnInfo(name = "userID")
    var userID: String,

    @ColumnInfo(name = "userName")
    var userName: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}
