package com.example.myganesha.APICalling.Decoration

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myganesha.R

class DecorationDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_decoration_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val DecorationImage = intent.getStringExtra("DecorationImage")
        val Description = intent.getStringExtra("Description")



        val imageImageView: ImageView = findViewById(R.id.image)
        val DescriptionTextView: TextView = findViewById(R.id.description)

        // Load the image using Glide
        Glide.with(this)
            .load(DecorationImage)
            .into(imageImageView)


        DescriptionTextView.text = Description
    }
}