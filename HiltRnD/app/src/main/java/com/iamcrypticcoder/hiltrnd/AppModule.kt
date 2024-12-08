package com.iamcrypticcoder.hiltrnd

import com.iamcrypticcoder.hiltrnd.activity.MainActivity
import com.iamcrypticcoder.hiltrnd.repo.CryptocurrencyRepository
import com.iamcrypticcoder.hiltrnd.repo.CryptocurrencyRepositoryDummy
import com.iamcrypticcoder.hiltrnd.repo.CryptocurrencyRepositoryImpl
import com.iamcrypticcoder.hiltrnd.repo.Dummy
import com.iamcrypticcoder.hiltrnd.repo.Impl
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