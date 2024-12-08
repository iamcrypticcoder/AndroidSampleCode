package com.iamcrypticcoder.hiltrnd.coffeemaker

import android.util.Log
import javax.inject.Inject

const val TAG = "RegularCoffeeMaker: "


class RegularCoffeeMaker @Inject constructor(
    @Regular private val coffee: Coffee,
    private val milk: Milk,
    @White private val sugar: Sugar
) : CoffeeMaker {
    override fun makeCoffee() : String {
        val output = "Making coffee with ${coffee.getName()}" +
                " with ${sugar.getName()} and ${milk.getName()}"
        Log.d(TAG, output)
        return output
    }
}