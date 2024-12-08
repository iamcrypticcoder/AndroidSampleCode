package com.iamcrypticcoder.hiltrnd.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.hiltrnd.coffeemaker.PremiumCoffeeMaker
import com.iamcrypticcoder.hiltrnd.databinding.ActivitySecondBinding
import com.iamcrypticcoder.hiltrnd.datastore.DataStore
import com.iamcrypticcoder.hiltrnd.datastore.DataStoreSQLite
import com.iamcrypticcoder.hiltrnd.datastore.SQLite
import com.iamcrypticcoder.hiltrnd.datastore.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    private lateinit var binding: ActivitySecondBinding

    @SQLite @Inject
    lateinit var sqliteStore3: DataStore
    @SQLite @Inject
    lateinit var sqliteStore4: DataStore
    @SharedPref @Inject
    lateinit var sharedPrefStore3: DataStore
    @SharedPref @Inject
    lateinit var sharedPrefStore4: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        Log.d(TAG, "SQLite Store 3 - $sqliteStore3")
        Log.d(TAG, "SQLite Store 4 - $sqliteStore4")
        Log.d(TAG, "SharedPref Store 3 - $sharedPrefStore3")
        Log.d(TAG, "SharedPref Store 4 - $sharedPrefStore4")
    }
}