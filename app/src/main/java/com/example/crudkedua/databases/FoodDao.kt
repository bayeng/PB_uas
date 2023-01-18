package com.example.crudkedua.databases

import androidx.room.*
import com.example.crudkedua.entities.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFood(food: Food)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFood(food: Food)
}