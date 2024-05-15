package com.example.myganesha.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myganesha.APICalling.Decoration.DecorationDetailsActivity
import com.example.myganesha.APICalling.EcoFriendly.BitmapUtils
import com.example.myganesha.APICalling.EcoFriendly.EcoFriendlyPojo
import com.example.myganesha.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class EcoAdapter (private val context: Context, private val EcoFriendlyPojo: EcoFriendlyPojo) : RecyclerView.Adapter<EcoAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val EcoImage : ImageView = itemView.findViewById(R.id.Eco_image)
        val downloadButton: ImageView = itemView.findViewById(R.id.downloadebtn)
        val shareButton: ImageView = itemView.findViewById(R.id.sharebtn)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.eco_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val EcoFriendly = EcoFriendlyPojo[position]
        Glide.with(holder.itemView)
            .load(EcoFriendly.murti_image) // Load image from URL
            .into(holder.EcoImage) // Set image into ImageView

        holder.downloadButton.setOnClickListener {
            val drawable = holder.EcoImage.drawable
            downloadImage(drawable)
        }

        holder.shareButton.setOnClickListener {
            val drawable = holder.EcoImage.drawable
            shareImage(drawable)
        }
    }

    override fun getItemCount(): Int {
        return  EcoFriendlyPojo.size
    }



    private fun downloadImage(drawable: Drawable) {
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val filename = "image_${System.currentTimeMillis()}.jpg"
            val saveDirectory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyGanesha")
            if (!saveDirectory.exists()) {
                saveDirectory.mkdirs()
            }
            val file = File(saveDirectory, filename)
            try {
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
                Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun shareImage(drawable: Drawable) {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val uri = BitmapUtils.getUriFromBitmap(context, bitmap)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share Image"))
    }
}

