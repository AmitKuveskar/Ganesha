package com.example.myganesha.APICalling.Decoration

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myganesha.MainActivity
import com.example.myganesha.R

class DecorationDetailsActivity : AppCompatActivity() {

    lateinit var Homebtn :ImageView
    lateinit var BackBtn :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_decoration_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Homebtn = findViewById(R.id.homebtn)
        BackBtn = findViewById(R.id.backbtn)

        Homebtn.setOnClickListener {
            val intent = Intent(this@DecorationDetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }

        BackBtn.setOnClickListener {
            val intent = Intent(this@DecorationDetailsActivity, APICallingActivity3::class.java)
            startActivity(intent)
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