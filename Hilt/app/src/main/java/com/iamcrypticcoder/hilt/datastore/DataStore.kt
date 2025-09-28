package com.iamcrypticcoder.hilt.datastore

interface DataStore {
    fun getDataStoreName() : String
    fun getData(): String
}