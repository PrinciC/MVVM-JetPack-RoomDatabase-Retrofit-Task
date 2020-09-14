package com.demo.princichristipracticaltask.Repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.princichristipracticaltask.Repository.User


@Dao
interface UserInfoDao {

    // insert user data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    // get all user data
    @Query("SELECT * from user_info ORDER BY user_name ASC")
    fun getAllUsers():LiveData<List<User>>

    // delete all user data
    @Query("DELETE FROM user_info")
    fun deleteAll()
}