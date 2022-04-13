package com.farhanfarkaann.belajarretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhanfarkaann.belajarretrofit.MainAdapter.*
import com.farhanfarkaann.belajarretrofit.databinding.ActivityMainBinding
import com.farhanfarkaann.belajarretrofit.model.GetAllCarResponseItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerViewView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        fetchAllData()
    }

    private fun fetchAllData(){
        ApiClient.instance.getAllCar()
            .enqueue( object : Callback<List<GetAllCarResponseItem>> {
                override fun onResponse(
                    call : Call<List<GetAllCarResponseItem>>,
                    response : Response<List<GetAllCarResponseItem>>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (code == 200) {
                        showList(body)
                        binding.plus.visibility = View.GONE
                    }else {
                        binding.plus.visibility = View.GONE
                    }
                }
                override fun onFailure(call: Call<List<GetAllCarResponseItem>> , t : Throwable) {
                    binding.plus.visibility = View.GONE
                }
            })
    }

    private fun showList(data: List<GetAllCarResponseItem>?) {
        val adapter = MainAdapter(object : OnClickListener {
            override fun onClickItem(data: GetAllCarResponseItem) {

            }
        })
        adapter.submitData(data)
        binding.recyclerViewView.adapter = adapter
    }
}

