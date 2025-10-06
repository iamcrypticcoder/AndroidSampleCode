package com.iamcrypticcoder.firebase.data.model

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("name")
    val name: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("username")
    val username: String,
    
    @SerializedName("password")
    val password: String,
    
    @SerializedName("phone")
    val phone: String? = null,
    
    @SerializedName("website")
    val website: String? = null
)
