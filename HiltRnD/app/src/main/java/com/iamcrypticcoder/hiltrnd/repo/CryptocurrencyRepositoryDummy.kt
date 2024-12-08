package com.iamcrypticcoder.hiltrnd.repo

class CryptocurrencyRepositoryDummy : CryptocurrencyRepository {
    override fun getCryptoCurrency() = listOf(
        // here we are adding images from wikipedia
        Cryptocurrency("1", "BitCoin"),
        Cryptocurrency("2", "Ethereum"),
        Cryptocurrency("3", "Binance"),
        Cryptocurrency("4", "DogeCoin"),
        Cryptocurrency("5", "LiteCoin"),
        Cryptocurrency("6", "Stellar"),
        Cryptocurrency("7", "Polkadot"),
    )
}