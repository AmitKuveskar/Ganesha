package com.example.myganesha.APICalling.Mandal

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
import java.util.Locale

class MandalDetailsActivity : AppCompatActivity() {

    lateinit var Homebtn :ImageView
    lateinit var BackBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mandal_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Homebtn = findViewById(R.id.homebtn)
        BackBtn = findViewById(R.id.backbtn)

        Homebtn.setOnClickListener {
            val intent = Intent(this@MandalDetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }

        BackBtn.setOnClickListener {
            val intent = Intent(this@MandalDetailsActivity, APICallingActivity2::class.java)
            startActivity(intent)
        }

        val KalakarName = intent.getStringExtra("MandalName")
        val KalakarImage = intent.getStringExtra("MandalImage")
        val Location = intent.getStringExtra("Location")
        val Route = intent.getStringExtra("Route")


        val imageImageView: ImageView = findViewById(R.id.image)
        val nameTextView: TextView = findViewById(R.id.Mandal_name)
        val LocationTextView: TextView = findViewById(R.id.location)
        val RouteTextView: TextView = findViewById(R.id.route)


        // Load the image using Glide
        Glide.with(this)
            .load(KalakarImage)
            .into(imageImageView)

        nameTextView.text = KalakarName
        LocationTextView.text = Location
        RouteTextView.text = Route
    }
}