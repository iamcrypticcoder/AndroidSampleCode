package com.iamcrypticcoder.hiltrnd.repo

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    @Impl private val repo : CryptocurrencyRepository
) : ViewModel() {
    fun getString() : String {
        return repo.getCryptoCurrency().first().image
    }
}