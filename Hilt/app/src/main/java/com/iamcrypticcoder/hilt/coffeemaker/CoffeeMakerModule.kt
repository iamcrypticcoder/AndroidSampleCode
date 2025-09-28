package com.iamcrypticcoder.hilt.coffeemaker

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoffeeMakerModule {

    @Regular
    @Provides
    fun provideRegularCoffee() : Coffee = RegularCoffee()

    @Premium
    @Provides
    fun providePremiumCoffee() : Coffee = PremiumCoffee()

    @Brown
    @Provides
    fun provideBrownSugar(): Sugar = BrownSugar()

    @White
    @Provides
    fun provideWhiteSugar(): Sugar = WhiteSugar()

    @Provides
    fun provideMilk(): Milk = Milk()

//    @Regular
//    @Provides
//    fun makeRegularCoffeeMaker(@Regular coffee: Coffee, milk: Milk, @White sugar: Sugar) : CoffeeMaker {
//        return RegularCoffeeMaker(coffee, milk, sugar)
//    }
//
//    @Premium
//    @Provides
//    fun makePremiumCoffeeMaker(@Premium coffee: Coffee, milk: Milk, @Brown sugar: Sugar) : CoffeeMaker {
//        return PremiumCoffeeMaker(coffee, milk, sugar)
//    }
}