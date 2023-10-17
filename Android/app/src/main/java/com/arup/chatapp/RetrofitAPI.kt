package com.arup.chatapp

import com.arup.chatapp.models.LoginModel
import com.arup.chatapp.models.LoginRequestModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("/login")
    fun loginData(
        @Body loginRequestModel: LoginRequestModel
    ): Call<LoginModel>
}
