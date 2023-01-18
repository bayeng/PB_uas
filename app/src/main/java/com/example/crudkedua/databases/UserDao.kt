package com.example.crudkedua.databases

import androidx.room.*
import com.example.crudkedua.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>
}