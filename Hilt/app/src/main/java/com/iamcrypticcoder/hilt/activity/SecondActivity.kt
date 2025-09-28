package com.iamcrypticcoder.hilt.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.hilt.coffeemaker.PremiumCoffeeMaker
import com.iamcrypticcoder.hilt.databinding.ActivitySecondBinding
import com.iamcrypticcoder.hilt.datastore.DataStore
import com.iamcrypticcoder.hilt.datastore.DataStoreSQLite
import com.iamcrypticcoder.hilt.datastore.SQLite
import com.iamcrypticcoder.hilt.datastore.SharedPref
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