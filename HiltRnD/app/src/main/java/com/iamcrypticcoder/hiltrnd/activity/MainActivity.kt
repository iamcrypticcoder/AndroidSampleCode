package com.iamcrypticcoder.hiltrnd.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.hiltrnd.coffeemaker.Coffee
import com.iamcrypticcoder.hiltrnd.coffeemaker.CoffeeMaker
import com.iamcrypticcoder.hiltrnd.coffeemaker.Premium
import com.iamcrypticcoder.hiltrnd.coffeemaker.PremiumCoffeeMaker
import com.iamcrypticcoder.hiltrnd.coffeemaker.Regular
import com.iamcrypticcoder.hiltrnd.coffeemaker.RegularCoffeeMaker
import com.iamcrypticcoder.hiltrnd.databinding.ActivityMainBinding
import com.iamcrypticcoder.hiltrnd.datastore.DataStore
import com.iamcrypticcoder.hiltrnd.datastore.DataStoreSQLite
import com.iamcrypticcoder.hiltrnd.datastore.SQLite
import com.iamcrypticcoder.hiltrnd.datastore.SharedPref
import com.iamcrypticcoder.hiltrnd.repo.CryptocurrencyRepository
import com.iamcrypticcoder.hiltrnd.repo.Dummy
import com.iamcrypticcoder.hiltrnd.repo.Impl
import com.iamcrypticcoder.hiltrnd.repo.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val TAG = "MainActivity"

    @Inject
    @Premium
    lateinit var coffee1: Coffee

    @Inject
    @Regular
    lateinit var coffee2: Coffee

    @Impl
    @Inject
    lateinit var repo: CryptocurrencyRepository

    @Inject @SQLite
    lateinit var sqliteStore1: DataStore

    @Inject @SQLite
    lateinit var sqliteStore2: DataStore

    @Inject @SharedPref
    lateinit var sharedPrefStore1: DataStore

    @Inject @SharedPref
    lateinit var sharedPrefStore2: DataStore

    @Inject
    lateinit var regularCoffeeMaker: RegularCoffeeMaker

    @Inject
    lateinit var premiumCoffeeMaker: PremiumCoffeeMaker

    @Inject
    lateinit var premiumCoffeeMaker2: PremiumCoffeeMaker

    private val viewModel : MyViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.textview1.text = dataStore.getData()
        binding.textview1.text = regularCoffeeMaker.makeCoffee()
            .plus("\n")
            .plus(premiumCoffeeMaker.makeCoffee())

        Log.d(TAG, premiumCoffeeMaker.toString())
        Log.d(TAG, premiumCoffeeMaker2.toString())

        Log.d(TAG, "SQLite Store 1 - $sqliteStore1")
        Log.d(TAG, "SQLite Store 2 - $sqliteStore2")
        Log.d(TAG, "SharedPref Store 1 - $sharedPrefStore1")
        Log.d(TAG, "SharedPref Store 2 - $sharedPrefStore2")

        binding.button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}