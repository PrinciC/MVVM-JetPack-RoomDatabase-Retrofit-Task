package com.demo.princichristipracticaltask.Repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.princichristipracticaltask.Repository.User


@Dao
interface UserInfoDao {

    //insert data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultModel: ResultModel)

    //get Data
    @Query("SELECT * FROM user_info  WHERE Username =:username")
    fun getLoginDetails(username: String?) : LiveData<ResultModel>
}