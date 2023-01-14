package com.example.crudkedua.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey(autoGenerate = false)
    val foodName: String
)