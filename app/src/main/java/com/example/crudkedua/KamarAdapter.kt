package com.example.crudkedua

import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudkedua.entities.Kamar
import kotlinx.android.synthetic.main.adapter_kamar.view.*

class KamarAdapter(private val kamars: ArrayList<Kamar>, private val listener: OnAdapterListener): RecyclerView.Adapter<KamarAdapter.KamarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KamarViewHolder {
        return KamarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_kamar, parent, false)
        )
    }

    override fun getItemCount() = kamars.size

    override fun onBindViewHolder(holder: KamarViewHolder, position: Int) {
        val kamars = kamars[position]
        holder.view.text_kamar.text = kamars.namaKamar
        holder.view.text_kamar.setOnClickListener{
            listener.onClick( kamars )
        }
        holder.view.icon_delete.setOnClickListener {
            listener.aDelete(kamars)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.aUpdate(kamars)
        }
    }



    class KamarViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Kamar>){
        kamars.clear()
        kamars.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(kamar: Kamar)
        fun aDelete(kamar: Kamar)
        fun aUpdate(kamar: Kamar)
    }



}

