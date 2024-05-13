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
import com.example.myganesha.APICalling.Mandal.MandalDetailsActivity
import com.example.myganesha.APICalling.Mandal.MandalPojo
import com.example.myganesha.R

class APIAdapter2 (context: Context, private val MandalPojo: MandalPojo) : RecyclerView.Adapter<APIAdapter2.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val MandalImage : ImageView = itemView.findViewById(R.id.Mandal_image)
        val MandalName : TextView = itemView.findViewById(R.id.Mandal_name)
        val cardView: CardView = itemView.findViewById(R.id.cardview2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mandal_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Mandal = MandalPojo[position]
        holder.MandalName.text = Mandal.title
        Glide.with(holder.itemView)
            .load(Mandal.image) // Load image from URL
            .into(holder.MandalImage) // Set image into ImageView
        holder.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MandalDetailsActivity::class.java)
            intent.putExtra("MandalImage", Mandal.thumb_image)
            intent.putExtra("MandalName", Mandal.title)
            intent.putExtra("Location", Mandal.location)
            intent.putExtra("Route", Mandal.how_to_reach)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return  MandalPojo.size
    }
}
