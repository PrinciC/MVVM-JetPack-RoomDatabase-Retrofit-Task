package com.demo.princichristipracticaltask.Repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.princichristipracticaltask.Repository.User


@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * from user_info ORDER BY user_name ASC")
    fun getAllUsers():LiveData<List<User>>

    @Query("DELETE FROM user_info")
    fun deleteAll()
}