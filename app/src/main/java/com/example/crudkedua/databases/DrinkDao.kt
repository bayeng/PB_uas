package com.example.crudkedua.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.crudkedua.entities.Drink

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDrink(drink: Drink)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDrink(drink: Drink)
}