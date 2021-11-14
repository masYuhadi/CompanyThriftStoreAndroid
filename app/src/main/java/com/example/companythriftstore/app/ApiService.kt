package com.example.companythriftstore.app

import com.example.companythriftstore.model.ResponseModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") nama :String,
        @Field("email") email :String,
        @Field("phone") phone :String,
        @Field("password") password :String
    ):Call<ResponseModel>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email :String,
        @Field("password") password :String
    ):Call<ResponseModel>

    @GET("produk")
    fun getproduk():Call<ResponseModel>
}