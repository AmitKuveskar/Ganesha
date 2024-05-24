package com.example.myganesha.APICalling.Mandal

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.widget.ImageView
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
import com.example.myganesha.adapter.APIAdapter2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity2 : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    lateinit var  Homebtn: ImageView
    lateinit var  Backbtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicalling2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv2)
        Homebtn = findViewById(R.id.homebtn)
        Backbtn = findViewById(R.id.backbtn)


        Homebtn.setOnClickListener {
            val intent = Intent(this@APICallingActivity2, MainActivity::class.java)
            startActivity(intent)
        }

        Backbtn.setOnClickListener {
            val intent = Intent(this@APICallingActivity2, MainActivity::class.java)
            startActivity(intent)

        }

        getData()

    }


    private fun getData() {
        RetrofitInstance.apiInterface.getData2().enqueue(object : Callback<MandalPojo?> {
            override fun onResponse(p0: Call<MandalPojo?>, p1: Response<MandalPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@APICallingActivity2)
                    recyclerView.layoutManager =LinearLayoutManager

                    val APIAdapter = APIAdapter2(this@APICallingActivity2, p1.body()!!)
                    recyclerView.adapter = APIAdapter

                    Toast.makeText(this@APICallingActivity2, "success", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@APICallingActivity2, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<MandalPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity2, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })

}
}