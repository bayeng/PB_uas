package com.example.crudkedua.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.crudkedua.entities.Kamar
import com.example.crudkedua.entities.User

@Dao
interface KamarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addKamar(kamar: Kamar)

    @Update
    fun updateKamar(kamar: Kamar)

    @Delete
    fun deleteKamar(kamar: Kamar)

    @Query("SELECT * FROM kamar ")
    fun getKamars(): List<Kamar>

    @Transaction
    @Query("SELECT * FROM kamar where kamarId = :kamar_id")
    fun getKamar(kamar_id: Int): List<Kamar>




}