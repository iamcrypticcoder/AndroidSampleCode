package com.iamcrypticcoder.hilt.repo

interface CryptocurrencyRepository {
    fun getCryptoCurrency() : List<Cryptocurrency>
}