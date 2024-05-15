package com.example.myganesha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myganesha.APICalling.AksharGanesha.APICallingActivity8
import com.example.myganesha.APICalling.EcoFriendly.APICallingActivity7
import com.example.myganesha.APICalling.Stories.APICallingActivity4

class GaneshaImgActivity : AppCompatActivity() {

    lateinit var Ecobtn :Button
    lateinit var  AksharGaneshaBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ganesha_img)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Ecobtn = findViewById(R.id.ecofriendly)
        AksharGaneshaBtn = findViewById(R.id.AksharGanesha)

        Ecobtn.setOnClickListener {
            val intent = Intent(this@GaneshaImgActivity, APICallingActivity7::class.java)
            startActivity(intent)
        }

        AksharGaneshaBtn.setOnClickListener {
            val intent = Intent(this@GaneshaImgActivity, APICallingActivity8::class.java)
            startActivity(intent)
        }
    }

}