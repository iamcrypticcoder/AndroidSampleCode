package com.iamcrypticcoder.hilt.coffeemaker

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Premium

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Regular

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class White

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Brown