package com.example.crudkedua.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = false)
    val namaUser: String,
)