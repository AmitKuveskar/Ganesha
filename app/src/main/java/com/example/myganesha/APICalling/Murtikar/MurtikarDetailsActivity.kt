package com.example.myganesha.APICalling.Murtikar

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

class MurtikarDetailsActivity : AppCompatActivity() {

    lateinit var Homebtn :ImageView
    lateinit var Backbtn :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_murtikar_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Homebtn = findViewById(R.id.homebtn)
        Backbtn = findViewById(R.id.backbtn)

        Homebtn.setOnClickListener {
            val intent = Intent (this@MurtikarDetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }

        Backbtn.setOnClickListener {
            val intent = Intent (this@MurtikarDetailsActivity, APICallingActivity::class.java)
            startActivity(intent)
        }


        val KalakarName = intent.getStringExtra("KalakarName")
        val KalakarImage = intent.getStringExtra("KalakarImage")
        val Contact = intent.getStringExtra("Contact")
        val Location = intent.getStringExtra("Location")


        val imageImageView: ImageView = findViewById(R.id.image)
        val nameTextView: TextView = findViewById(R.id.Kalakar_name)
        val ContactTextView: TextView = findViewById(R.id.contact)
        val LocationTextView: TextView = findViewById(R.id.location)


        // Load the image using Glide
        Glide.with(this)
            .load(KalakarImage)
            .into(imageImageView)

        nameTextView.text = KalakarName
        ContactTextView.text = Contact
        LocationTextView.text = Location
    }
}