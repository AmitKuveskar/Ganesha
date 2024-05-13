package com.example.myganesha.APICalling.Decoration

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.Mandal.MandalPojo
import com.example.myganesha.APICalling.RetrofitInstance
import com.example.myganesha.R
import com.example.myganesha.adapter.APIAdapter2
import com.example.myganesha.adapter.APIAdapter3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity3 : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicalling3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv3)
        getData()
    }



    private fun getData() {
        RetrofitInstance.apiInterface.getData3().enqueue(object : Callback<DecorationPojo?> {
            override fun onResponse(p0: Call<DecorationPojo?>, p1: Response<DecorationPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val GridLayoutManager = GridLayoutManager(this@APICallingActivity3,2)
                    recyclerView.layoutManager = GridLayoutManager

                    val APIAdapter = APIAdapter3(this@APICallingActivity3, p1.body()!!)
                    recyclerView.adapter = APIAdapter

                    Toast.makeText(this@APICallingActivity3, "success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@APICallingActivity3, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<DecorationPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity3, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

}