package com.example.crudkedua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudkedua.databases.HotelDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserActivity : AppCompatActivity() {

    val db by lazy { HotelDB(this) }
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userRecycleView()
        mainActivityListener()
    }

    fun mainActivityListener(){
        button_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val users= db.userDao().getUsers()
            Log.d("MainActivity","dbResponse: $users")
            withContext(Dispatchers.Main){
                userAdapter.setData(users)
            }
        }
    }

    private fun userRecycleView(){
        userAdapter = UserAdapter(arrayListOf())
        list_user.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = userAdapter
        }
    }


}