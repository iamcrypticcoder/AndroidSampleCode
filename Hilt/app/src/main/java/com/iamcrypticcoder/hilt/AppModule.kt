package com.iamcrypticcoder.hilt

import com.iamcrypticcoder.hilt.activity.MainActivity
import com.iamcrypticcoder.hilt.repo.CryptocurrencyRepository
import com.iamcrypticcoder.hilt.repo.CryptocurrencyRepositoryDummy
import com.iamcrypticcoder.hilt.repo.CryptocurrencyRepositoryImpl
import com.iamcrypticcoder.hilt.repo.Dummy
import com.iamcrypticcoder.hilt.repo.Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
object AppModule {

    @Impl
    @Provides
    fun provideCryptocurrencyRepositoryImpl(): CryptocurrencyRepository = CryptocurrencyRepositoryImpl()

    @Dummy
    @Provides
    fun provideCryptocurrencyRepositoryDummy(): CryptocurrencyRepository = CryptocurrencyRepositoryDummy()

    @Provides
    fun provideMyDependency() : String {
        return "This is magic string"
    }
}