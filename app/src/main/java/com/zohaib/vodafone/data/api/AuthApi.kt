package com.zohaib.vodafone.data.api

import com.zohaib.vodafone.data.model.LoginRequest
import com.zohaib.vodafone.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse


    @GET("auth/me")
    suspend fun me(@Header("Authorization") bearer: String): LoginResponse
}