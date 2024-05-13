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
import com.example.myganesha.APICalling.Stories.APICallingActivity5
import com.example.myganesha.APICalling.Stories.StoriesPojo
import com.example.myganesha.R


class StoriesAdapter(private val context: Context, private val StoriesPojo: StoriesPojo) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val StoriesImage : ImageView = itemView.findViewById(R.id.Stories_image)
        val Name : TextView = itemView.findViewById(R.id.name)
        val cardView: CardView = itemView.findViewById(R.id.cardview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stories_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Stories = StoriesPojo[position]
        holder.Name.text = Stories.cat_name
        Glide.with(holder.itemView)
            .load(Stories.cat_image) // Load image from URL
            .into(holder.StoriesImage) // Set image into ImageView
        holder.cardView.setOnClickListener {
            val intent = Intent(context, APICallingActivity5::class.java)
            intent.putExtra("id", Stories.id)
            context.startActivity(intent)


        }

    }

    override fun getItemCount(): Int {
        return  StoriesPojo.size
    }
}
