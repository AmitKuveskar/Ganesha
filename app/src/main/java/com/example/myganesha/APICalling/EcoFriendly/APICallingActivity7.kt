package com.example.myganesha.APICalling.EcoFriendly

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.Decoration.DecorationPojo
import com.example.myganesha.APICalling.RetrofitInstance
import com.example.myganesha.GaneshaImgActivity
import com.example.myganesha.MainActivity
import com.example.myganesha.R
import com.example.myganesha.adapter.APIAdapter3
import com.example.myganesha.adapter.EcoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity7 : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    lateinit var  home :ImageView
    lateinit var backbtn :ImageView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicalling7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv7)
        home = findViewById(R.id.homebtn)
        backbtn = findViewById(R.id.backbtn)
        progressBar = findViewById(R.id.progressbar)


        home.setOnClickListener {
            val intent = Intent (this@APICallingActivity7,MainActivity::class.java)
            startActivity(intent)
        }

        backbtn.setOnClickListener {
            val intent = Intent (this@APICallingActivity7,GaneshaImgActivity::class.java)
            startActivity(intent)
        }
        getData()
    }

    private fun getData() {
        RetrofitInstance.apiInterface.eco(1.toString()).enqueue(object : Callback<EcoFriendlyPojo?> {
            override fun onResponse(p0: Call<EcoFriendlyPojo?>, p1: Response<EcoFriendlyPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {
                    progressBar.visibility = View.GONE

                    val GridLayoutManager = GridLayoutManager(this@APICallingActivity7,2)
                    recyclerView.layoutManager = GridLayoutManager

                    val EcoAdapter = EcoAdapter(this@APICallingActivity7, p1.body()!!)
                    recyclerView.adapter = EcoAdapter

                    Toast.makeText(this@APICallingActivity7, "success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@APICallingActivity7, "error", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(p0: Call<EcoFriendlyPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity7, ""+p1.message, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        })

    }

}