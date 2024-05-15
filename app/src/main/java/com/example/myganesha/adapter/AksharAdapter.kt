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
import com.example.myganesha.APICalling.AksharGanesha.AksharPojo
import com.example.myganesha.APICalling.AksharGanesha.AksharPojoItem
import com.example.myganesha.APICalling.Murtikar.MurtikarDetailsActivity
import com.example.myganesha.R

class AksharAdapter(private val context: Context, private var originalList: List<AksharPojoItem>) : RecyclerView.Adapter<AksharAdapter.ViewHolder>() {

    private var filteredList: List<AksharPojoItem> = originalList

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val aksharImage: ImageView = itemView.findViewById(R.id.Akshar_image)
        val aksharName: TextView = itemView.findViewById(R.id.Akshar_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.akshar_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val akshar = filteredList[position]
        holder.aksharName.text = akshar.name
        Glide.with(holder.itemView)
            .load(akshar.image) // Load image from URL
            .into(holder.aksharImage) // Set image into ImageView
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    // Method to perform search based on query
    fun performSearch(query: String) {
        if (query.isEmpty()) {
            filteredList = originalList // Reset to original list if query is empty
        } else {
            filteredList = originalList.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged() // Notify adapter that data set has changed
    }
}
