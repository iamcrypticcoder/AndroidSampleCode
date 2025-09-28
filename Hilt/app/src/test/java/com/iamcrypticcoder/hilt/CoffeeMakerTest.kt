package com.iamcrypticcoder.hilt

import com.iamcrypticcoder.hilt.coffeemaker.CoffeeMaker
import com.iamcrypticcoder.hilt.coffeemaker.Premium
import org.junit.Test
import javax.inject.Inject

class CoffeeMakerTest {

    @Inject @Premium
    lateinit var coffeeMaker: CoffeeMaker

    @Test
    fun testCoffeeMaker() {
        coffeeMaker.makeCoffee()
    }
}