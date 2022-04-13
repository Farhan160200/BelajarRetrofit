package com.farhanfarkaann.belajarretrofit.service

import com.farhanfarkaann.belajarretrofit.model.GetAllCarResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    //GET digunakan untuk memanggil semua data yang terdapat pada server
    @GET("admin/car")
    fun getAllCar(): Call<List<GetAllCarResponseItem>>
}