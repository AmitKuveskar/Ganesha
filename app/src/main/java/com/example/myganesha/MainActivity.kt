package com.example.myganesha

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myganesha.APICalling.Decoration.APICallingActivity3
import com.example.myganesha.APICalling.Decoration.DecorationPojo
import com.example.myganesha.APICalling.Murtikar.APICallingActivity
import com.example.myganesha.APICalling.Mandal.APICallingActivity2
import com.example.myganesha.APICalling.Stories.APICallingActivity4
import com.example.myganesha.APICalling.Stories.StoryDetailsActivity
import com.example.myganesha.adapter.StoriesAdapter

class MainActivity : AppCompatActivity() {

    lateinit var Ganeshbtn: Button
    lateinit var Murtikarbtn : Button
    lateinit var Mandalbtn : Button
    lateinit var Decorationbtn : Button
    lateinit var Storiesbtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Ganeshbtn = findViewById(R.id.circular_button)
        Murtikarbtn = findViewById(R.id.circular_button2)
        Mandalbtn = findViewById(R.id.circular_button3)
        Decorationbtn = findViewById(R.id.circular_button4)
        Storiesbtn = findViewById(R.id.circular_button6)

        Ganeshbtn.setOnClickListener {
        val intent = Intent(this, GaneshaImgActivity::class.java)
        startActivity(intent)
    }
        Murtikarbtn.setOnClickListener {
            val intent = Intent(this, APICallingActivity::class.java)
            startActivity(intent)
        }

        Mandalbtn.setOnClickListener {
            val intent = Intent(this, APICallingActivity2::class.java)
            startActivity(intent)
        }

        Decorationbtn.setOnClickListener {
            val intent = Intent(this, APICallingActivity3::class.java)
            startActivity(intent)
        }

        Storiesbtn.setOnClickListener {
            val intent = Intent(this, APICallingActivity4::class.java)
            startActivity(intent)
        }
    }
}