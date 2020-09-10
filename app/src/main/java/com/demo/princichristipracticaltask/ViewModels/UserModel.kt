package com.demo.princichristipracticaltask.ViewModels


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user_info")
data class UserModel(
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "user_name")
        var userName:String ,
        @NotNull
        @ColumnInfo(name = "user_password")
        var userPassword:String
)    {

}