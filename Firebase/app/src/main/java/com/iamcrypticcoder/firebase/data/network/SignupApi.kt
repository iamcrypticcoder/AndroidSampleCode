package com.iamcrypticcoder.firebase.data.network

import com.iamcrypticcoder.firebase.data.model.SignupRequest
import com.iamcrypticcoder.firebase.data.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApi {
    
    @POST("auth/signup")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>
}
