package com.example.crudkedua.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Kamar(
    @PrimaryKey(autoGenerate = true)
    val kamarId:Int,
    val namaKamar:String,
    @ForeignKey(entity = User::class, parentColumns = ["namaUser"], childColumns = ["namaPemesan"])
    val namaPemesan: String,
    @ForeignKey(entity = Food::class, parentColumns = ["foodName"], childColumns = ["foodDelivered"])
    val foodDelivered: String,
    @ForeignKey(entity = Drink::class, parentColumns = ["drinkName"], childColumns = ["drinkDelivered"])
    val drinkDelivered: String

        )