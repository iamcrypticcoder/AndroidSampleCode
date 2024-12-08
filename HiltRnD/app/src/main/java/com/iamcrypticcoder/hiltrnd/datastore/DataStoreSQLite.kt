package com.iamcrypticcoder.hiltrnd.datastore


class DataStoreSQLite : DataStore {
    override fun getDataStoreName(): String {
        return "DataStoreSQLite"
    }

    override fun getData(): String {
        return "Data from SQLite"
    }
}