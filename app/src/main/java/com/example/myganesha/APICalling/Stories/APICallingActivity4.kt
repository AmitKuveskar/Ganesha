package com.example.myganesha.APICalling.Stories

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.RetrofitInstance
import com.example.myganesha.R
import com.example.myganesha.adapter.StoriesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity4 : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicalling4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)
        getData()
    }

    private fun getData() {
        RetrofitInstance.apiInterface.stories().enqueue(object : Callback<StoriesPojo?> {
            override fun onResponse(p0: Call<StoriesPojo?>, p1: Response<StoriesPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@APICallingActivity4)
                    recyclerView.layoutManager =LinearLayoutManager

                    val StoriesAdapter = StoriesAdapter(this@APICallingActivity4, p1.body()!!)
                    recyclerView.adapter = StoriesAdapter

                    Toast.makeText(this@APICallingActivity4, "success", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@APICallingActivity4, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<StoriesPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity4, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
