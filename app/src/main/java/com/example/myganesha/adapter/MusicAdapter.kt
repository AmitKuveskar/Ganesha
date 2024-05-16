package com.example.myganesha.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.Aarti.AartiPojo
import com.example.myganesha.APICalling.Aarti.MusicPlayerActivity
import com.example.myganesha.APICalling.Stories.APICallingActivity5
import com.example.myganesha.R


class MusicAdapter(private val context: Context, private val aartiPojoList: AartiPojo) : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val musicTitle: TextView = itemView.findViewById(R.id.Music_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aarti = aartiPojoList[position]
        holder.musicTitle.text = aarti.title
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MusicPlayerActivity::class.java)
            intent.putExtra("id", aarti.id)
            intent.putParcelableArrayListExtra("songList", ArrayList(aartiPojoList))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return aartiPojoList.size
    }
}
