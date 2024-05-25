package com.example.myganesha.APICalling.AksharGanesha

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.RetrofitInstance
import com.example.myganesha.GaneshaImgActivity
import com.example.myganesha.MainActivity
import com.example.myganesha.R
import com.example.myganesha.adapter.AksharAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity8 : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    lateinit var  SearchButton : AppCompatImageButton
    lateinit var  searchEditText : TextView
    lateinit var  HomeBtn : ImageView
    lateinit var  BackBtn : ImageView
    lateinit var  progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicalling8)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)
        SearchButton = findViewById(R.id.searchButton)
        searchEditText = findViewById(R.id.searchEditText)
        HomeBtn = findViewById(R.id.homebtn)
        BackBtn = findViewById(R.id.backbtn)
        progressBar = findViewById(R.id.progressbar)

        HomeBtn.setOnClickListener {
            val intent = Intent(this@APICallingActivity8, MainActivity::class.java)
            startActivity(intent)
        }

        BackBtn.setOnClickListener {
            val intent = Intent(this@APICallingActivity8, GaneshaImgActivity::class.java)
            startActivity(intent)
        }


        getData()


    }

    private fun getData() {
        RetrofitInstance.apiInterface.akshar().enqueue(object : Callback<AksharPojo?> {
            override fun onResponse(p0: Call<AksharPojo?>, p1: Response<AksharPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {
                    progressBar.visibility = View.GONE

                    val LinearLayoutManager = LinearLayoutManager(this@APICallingActivity8)
                    recyclerView.layoutManager =LinearLayoutManager

                    val AksharAdapter = AksharAdapter(this@APICallingActivity8, p1.body()!!)
                    recyclerView.adapter = AksharAdapter

                    SearchButton.setOnClickListener {
                        val query = searchEditText.text.toString().trim()
                        AksharAdapter.performSearch(query)
                    }

                    Toast.makeText(this@APICallingActivity8, "success", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@APICallingActivity8, "error", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(p0: Call<AksharPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity8, ""+p1.message, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        })
    }
}
