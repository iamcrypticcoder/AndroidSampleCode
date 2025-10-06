package com.iamcrypticcoder.firebase.data.network

import com.iamcrypticcoder.firebase.data.model.LoginRequest
import com.iamcrypticcoder.firebase.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
