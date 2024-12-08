package com.iamcrypticcoder.hiltrnd

import com.iamcrypticcoder.hiltrnd.coffeemaker.CoffeeMaker
import com.iamcrypticcoder.hiltrnd.coffeemaker.Premium
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