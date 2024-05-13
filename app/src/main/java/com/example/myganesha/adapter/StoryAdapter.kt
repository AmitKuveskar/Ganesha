package com.example.myganesha.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myganesha.APICalling.Stories.StoryPojo
import com.example.myganesha.R

class StoryAdapter(context: Context, private val StoryPojo: StoryPojo) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val StoryImage : ImageView = itemView.findViewById(R.id.Story_image)
        val title : TextView = itemView.findViewById(R.id.name)
        val description : TextView = itemView.findViewById(R.id.description)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Story = StoryPojo[position]
        holder.title.text = Story.title
        Glide.with(holder.itemView)
            .load(Story.image) // Load image from URL
            .into(holder.StoryImage) // Set image into ImageView
        holder.description.text = Story.description
    }


    override fun getItemCount(): Int {
        return  StoryPojo.size
    }
}
