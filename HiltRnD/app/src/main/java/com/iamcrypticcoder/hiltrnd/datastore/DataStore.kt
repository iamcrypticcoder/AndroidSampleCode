package com.iamcrypticcoder.hiltrnd.datastore

interface DataStore {
    fun getDataStoreName() : String
    fun getData(): String
}