package com.example.myganesha.APICalling.Stories

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.RetrofitInstance
import com.example.myganesha.MainActivity
import com.example.myganesha.R
import com.example.myganesha.adapter.StoryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity5 : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var HomeBtn: ImageView
    lateinit var BackBtn: ImageView
    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicalling5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv5)
        HomeBtn = findViewById(R.id.homebtn)
        BackBtn = findViewById(R.id.backbtn)
        progressBar = findViewById(R.id.progressbar)

        HomeBtn.setOnClickListener {
            val intent = Intent(this@APICallingActivity5, MainActivity::class.java)
            startActivity(intent)
        }

        BackBtn.setOnClickListener {
            val intent = Intent(this@APICallingActivity5, APICallingActivity4::class.java)
            startActivity(intent)
        }
        val id = intent.getStringExtra("id")

        if (id != null) {
            getData(id)
        }
    }

    private fun getData(id : String) {
        RetrofitInstance.apiInterface.story(id).enqueue(object : Callback<StoryPojo?> {
            override fun onResponse(p0: Call<StoryPojo?>, p1: Response<StoryPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@APICallingActivity5)
                    recyclerView.layoutManager =LinearLayoutManager

                    val StoryAdapter = StoryAdapter(this@APICallingActivity5, p1.body()!!)
                    recyclerView.adapter = StoryAdapter

                    Toast.makeText(this@APICallingActivity5, "success", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@APICallingActivity5, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<StoryPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity5, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
