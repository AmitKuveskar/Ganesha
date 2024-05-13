package com.example.myganesha.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myganesha.APICalling.Murtikar.MurtikarDetailsActivity
import com.example.myganesha.APICalling.Murtikar.MurtikarPojo
import com.example.myganesha.R

class APIAdapter(context: Context, private val MurtikarPojo: MurtikarPojo) :
    RecyclerView.Adapter<APIAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val KalakarImage : ImageView = itemView.findViewById(R.id.Kalakar_image)
        val KalakarName : TextView = itemView.findViewById(R.id.Kalakar_name)
        val cardView: CardView = itemView.findViewById(R.id.cardview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.murtikar_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val murtikar = MurtikarPojo[position]
        holder.KalakarName.text = murtikar.name
        Glide.with(holder.itemView)
            .load(murtikar.image) // Load image from URL
            .into(holder.KalakarImage) // Set image into ImageView
        holder.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MurtikarDetailsActivity::class.java)
            intent.putExtra("KalakarImage", murtikar.thumb_image)
            intent.putExtra("KalakarName", murtikar.name)
            intent.putExtra("Contact", murtikar.contact)
            intent.putExtra("Location", murtikar.location)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return  MurtikarPojo.size
    }
}
