package com.arup.chatapp.utils

import android.content.Context
import com.arup.chatapp.R
import com.arup.chatapp.RetrofitAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofitAPI(context: Context): RetrofitAPI {
    val retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.server_url))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(RetrofitAPI::class.java)
}