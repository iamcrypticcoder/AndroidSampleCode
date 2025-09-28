package com.iamcrypticcoder.hilt.datastore


class DataStoreSharedPref : DataStore {
    override fun getDataStoreName(): String {
        return "DataStoreSharedPref"
    }

    override fun getData(): String {
        return "Data from Shared Pref"
    }
}