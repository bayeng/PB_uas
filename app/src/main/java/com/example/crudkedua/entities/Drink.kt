package com.example.crudkedua.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Drink(
    @PrimaryKey(autoGenerate = false)
    val drinkName: String
)