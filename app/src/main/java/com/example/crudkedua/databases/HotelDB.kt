package com.example.crudkedua.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crudkedua.entities.Drink
import com.example.crudkedua.entities.Food
import com.example.crudkedua.entities.Kamar
import com.example.crudkedua.entities.User

@Database(
    entities = [Kamar::class,User::class,Drink::class,Food::class],
    version = 1
)
abstract class HotelDB : RoomDatabase(){

    abstract fun kamarDao() : KamarDao
    abstract fun foodDao() : FoodDao
    abstract fun drinkDao() : DrinkDao
    abstract fun userDao() : UserDao

    companion object {

        @Volatile private var instance : HotelDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HotelDB::class.java,
            "hotel123.db"
        ).build()

    }
}