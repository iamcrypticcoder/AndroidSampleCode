package com.iamcrypticcoder.hiltrnd.repo

interface CryptocurrencyRepository {
    fun getCryptoCurrency() : List<Cryptocurrency>
}