package com.iamcrypticcoder.hilt.coffeemaker

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PremiumCoffeeMaker @Inject constructor(
    @Premium private val coffee: Coffee,
    private val milk: Milk,
    @Brown private val sugar: Sugar
): CoffeeMaker {
    override fun makeCoffee() : String {
        val output = "Making coffee with ${coffee.getName()}" +
                " with ${sugar.getName()} and ${milk.getName()}"
        Log.d(TAG, output)
        return output
    }
}