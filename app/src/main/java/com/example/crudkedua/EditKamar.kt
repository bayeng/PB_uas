package com.example.crudkedua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.crudkedua.databases.HotelDB
import com.example.crudkedua.entities.Drink
import com.example.crudkedua.entities.Food
import com.example.crudkedua.entities.Kamar
import com.example.crudkedua.entities.User
import com.example.crudkedua.helper.Helper
import kotlinx.android.synthetic.main.kamar_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditKamar : AppCompatActivity() {

    val db by lazy { HotelDB(this) }
    private var kamarId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kamar_edit)

        kamarListener()
        kamarId = intent.getIntExtra("intent_id",0)
        setupView()
//        getKamar()
    }

    fun getKamar(){
        kamarId = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            var kamar = db.kamarDao().getKamar(kamarId).get(0)
            edit_namaKamar.setText(kamar.namaKamar)
            edit_namaPemesan.setText(kamar.namaPemesan)
            edit_food.setText(kamar.foodDelivered)
            edit_drink.setText(kamar.drinkDelivered)
        }
    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType){
            Helper.TYPE_CREATE->{
                button_update.visibility = View.GONE
            }
            Helper.TYPE_READ->{
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getKamar()
            }
            Helper.TYPE_UPDATE->{
                button_save.visibility = View.GONE
                getKamar()
            }
        }
    }

    fun kamarListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.kamarDao().addKamar(
                    Kamar(0,edit_namaKamar.text.toString(),edit_namaPemesan.text.toString(),edit_food.text.toString(),edit_drink.text.toString())
                )
                db.userDao().updateUser(
                    User(edit_namaPemesan.text.toString())
                )
                db.foodDao().updateFood(
                    Food(edit_food.text.toString())
                )
                db.drinkDao().updateDrink(
                    Drink(edit_food.text.toString())
                )
                finish()
            }
        }

        button_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.kamarDao().updateKamar(
                    Kamar(kamarId,edit_namaKamar.text.toString(),edit_namaPemesan.text.toString(),edit_food.text.toString(),edit_drink.text.toString())
                )
                db.userDao().addUser(
                    User(edit_namaPemesan.text.toString())
                )
                db.foodDao().addFood(
                    Food(edit_food.text.toString())
                )
                db.drinkDao().addDrink(
                    Drink(edit_food.text.toString())
                )
                finish()
            }
        }
    }
}