package com.example.myganesha.APICalling.EcoFriendly

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myganesha.APICalling.Decoration.DecorationPojo
import com.example.myganesha.APICalling.RetrofitInstance
import com.example.myganesha.R
import com.example.myganesha.adapter.APIAdapter3
import com.example.myganesha.adapter.EcoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICallingActivity7 : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView

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
        getData()
    }

    private fun getData() {
        RetrofitInstance.apiInterface.eco(1.toString()).enqueue(object : Callback<EcoFriendlyPojo?> {
            override fun onResponse(p0: Call<EcoFriendlyPojo?>, p1: Response<EcoFriendlyPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val GridLayoutManager = GridLayoutManager(this@APICallingActivity7,2)
                    recyclerView.layoutManager = GridLayoutManager

                    val EcoAdapter = EcoAdapter(this@APICallingActivity7, p1.body()!!)
                    recyclerView.adapter = EcoAdapter

                    Toast.makeText(this@APICallingActivity7, "success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@APICallingActivity7, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<EcoFriendlyPojo?>, p1: Throwable) {
                Toast.makeText(this@APICallingActivity7, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

}