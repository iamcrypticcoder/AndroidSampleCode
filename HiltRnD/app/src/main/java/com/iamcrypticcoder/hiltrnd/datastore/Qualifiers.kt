package com.iamcrypticcoder.hiltrnd.datastore

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SQLite

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SharedPref