package com.example.crudkedua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudkedua.databases.HotelDB
import com.example.crudkedua.entities.Kamar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.crudkedua.helper.Helper



class MainActivity : AppCompatActivity() {

    val db by lazy { HotelDB(this) }
    lateinit var kamarAdapter: KamarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kamarListener()
        kamarRecycleView()
        userListener()

    }

    fun userListener(){
        button_list.setOnClickListener{
            startActivity(Intent(this,UserActivity::class.java))
        }
    }

    fun kamarListener(){
        button_create.setOnClickListener{
            startActivity(Intent(this,PesanKamar::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        loadDataKamar()
    }

    fun loadDataKamar(){
        CoroutineScope(Dispatchers.IO).launch {
            val kamars= db.kamarDao().getKamars()
            Log.d("MainActivity","dbResponse: $kamars")
            withContext(Dispatchers.Main){
                kamarAdapter.setData(kamars)
            }
        }
    }
    fun intentCreate(kamarId: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, PesanKamar::class.java)
                .putExtra("intent_id", kamarId)
                .putExtra("intent_type", intentType)
        )
    }
    fun intentEdit(kamarId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext,EditKamar::class.java)
                .putExtra("intent_id",kamarId)
                .putExtra("intent_type",intentType)
        )

    }

    private fun kamarRecycleView(){
        kamarAdapter = KamarAdapter(arrayListOf(), object :KamarAdapter.OnAdapterListener{
            override fun onClick(kamar: Kamar) {
                intentEdit(kamar.kamarId, Helper.TYPE_READ)
            }

            override fun aDelete(kamar: Kamar) {
                CoroutineScope(Dispatchers.IO).launch {
                    val kamars= db.kamarDao().deleteKamar(kamar)
                    loadDataKamar()
                }
            }

            override fun aUpdate(kamar: Kamar) {
                intentEdit(kamar.kamarId,Helper.TYPE_UPDATE)
            }

        })
        list_kamar.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = kamarAdapter
        }
    }



}