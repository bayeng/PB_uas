package com.example.crudkedua

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudkedua.entities.Kamar
import com.example.crudkedua.entities.User
import kotlinx.android.synthetic.main.adapter_kamar.view.*
import kotlinx.android.synthetic.main.adapter_user.view.*

class UserAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_user, parent, false)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.view.text_user.text = user.namaUser
    }



    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<User>){
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }

    interface UserAdapter{
       fun onClick(kamar: Kamar)
    }



}
