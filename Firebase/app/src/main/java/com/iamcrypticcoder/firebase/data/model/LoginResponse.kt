package com.iamcrypticcoder.firebase.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    
    @SerializedName("user")
    val user: User,
    
    @SerializedName("message")
    val message: String,
    
    @SerializedName("success")
    val success: Boolean
)
