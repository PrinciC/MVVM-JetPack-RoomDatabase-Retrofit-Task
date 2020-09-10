package com.mvvm.kot.Kotlin_Retrofit_Room.Repository


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_info")
class ResultModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private var id: Int = 0
    @NonNull
    @ColumnInfo(name = "user_name")
    @SerializedName("body")
    private var body: String? = null
    @NonNull
    @ColumnInfo(name = "user_password")
    @SerializedName("title")
    private var title: String? = null


    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getBody(): String? {
        return body
    }

    fun setBody(body: String) {
        this.body = body
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }



    override fun toString(): String {
        return "ClassPojo [id = $id, body = $body, title = $title]"
    }
}