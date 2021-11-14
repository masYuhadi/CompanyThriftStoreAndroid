package com.example.companythriftstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.companythriftstore.R
import com.example.companythriftstore.model.Produk
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class AdapterProduk(var data:ArrayList<Produk>):RecyclerView.Adapter<AdapterProduk.Holder>(){
    class Holder(view: View):RecyclerView.ViewHolder(view){
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val imgProduk = view.findViewById<ImageView>(R.id.img_produk)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].name
        holder.tvHarga.text =NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf( data[position].harga))
//        holder.imgProduk.setImageResource(data[position].image)

        val imege ="http://172.16.101.8:8000/storage/produk/"+data[position].image
        Picasso.get()
            .load(imege)
            .placeholder(R.drawable.packaging)
            .error(R.drawable.packaging)
            .into(holder.imgProduk)
    }

}