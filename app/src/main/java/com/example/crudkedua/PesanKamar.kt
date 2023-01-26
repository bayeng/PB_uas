package com.example.crudkedua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudkedua.databases.HotelDB
import com.example.crudkedua.entities.Drink
import com.example.crudkedua.entities.Food
import com.example.crudkedua.entities.Kamar
import com.example.crudkedua.entities.User
import kotlinx.android.synthetic.main.activity_pesan_kamar.*
import kotlinx.android.synthetic.main.kamar_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PesanKamar : AppCompatActivity() {

    val db by lazy { HotelDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_kamar)

        kamarListener()
    }

    fun kamarListener() {
        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.kamarDao().addKamar(
                    Kamar(
                        0,
                      sp_kamar.selectedItem.toString(),
                        user.text.toString(),
                        sp_makanan.selectedItem.toString(),
                        sp_minuman.selectedItem.toString()
                    )
                )
                db.userDao().addUser(
                    User(user.text.toString(),)
                )
                db.foodDao().addFood(
                    Food(sp_makanan.selectedItem.toString())
                )
                db.drinkDao().addDrink(
                    Drink(sp_minuman.selectedItem.toString())
                )
                finish()
            }
        }
    }
}