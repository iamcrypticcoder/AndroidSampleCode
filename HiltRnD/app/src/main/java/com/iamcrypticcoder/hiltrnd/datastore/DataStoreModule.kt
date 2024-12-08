package com.iamcrypticcoder.hiltrnd.datastore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object DataStoreModule {

    @SQLite
    @Provides
    @ActivityScoped
    fun provideDataStoreSQLite() : DataStore {
        return DataStoreSQLite()
    }

    @SharedPref
    @Provides
    fun provideDataStoreSharedPref() : DataStore {
        return DataStoreSharedPref()
    }
}