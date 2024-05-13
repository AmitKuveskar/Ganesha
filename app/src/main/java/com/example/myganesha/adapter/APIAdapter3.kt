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
import com.example.myganesha.APICalling.Decoration.DecorationDetailsActivity
import com.example.myganesha.APICalling.Decoration.DecorationPojo
import com.example.myganesha.APICalling.Mandal.MandalDetailsActivity
import com.example.myganesha.APICalling.Mandal.MandalPojo
import com.example.myganesha.R

class APIAdapter3 (context: Context, private val DecorationPojo: DecorationPojo) : RecyclerView.Adapter<APIAdapter3.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val DecorationImage : ImageView = itemView.findViewById(R.id.Decoration_image)
        val cardView: CardView = itemView.findViewById(R.id.cardview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.decoration_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Decoration = DecorationPojo[position]
        Glide.with(holder.itemView)
            .load(Decoration.image) // Load image from URL
            .into(holder.DecorationImage) // Set image into ImageView
        holder.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DecorationDetailsActivity::class.java)
            intent.putExtra("DecorationImage", Decoration.thumb_image)
            intent.putExtra("Description", Decoration.description)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return  DecorationPojo.size
    }
}
