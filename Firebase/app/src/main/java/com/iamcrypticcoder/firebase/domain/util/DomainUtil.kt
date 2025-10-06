package com.iamcrypticcoder.firebase.domain.util

object DomainUtil {
    fun phoneNumberToEmail(phoneNumber: String) : String {
        return "${phoneNumber}@domain.com";
    }
}