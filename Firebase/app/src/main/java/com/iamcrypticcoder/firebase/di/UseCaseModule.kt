package com.iamcrypticcoder.firebase.di

import com.iamcrypticcoder.firebase.domain.usecase.LoginUseCase
import com.iamcrypticcoder.firebase.domain.usecase.SignUpUseCase
import com.iamcrypticcoder.firebase.domain.usecase.impl.LoginUseCaseImpl
import com.iamcrypticcoder.firebase.domain.usecase.impl.SignupUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(useCase: LoginUseCaseImpl) : LoginUseCase {
        return useCase
    }

    @Provides
    fun provideSignUpUseCase(useCase: SignupUseCaseImpl) : SignUpUseCase {
        return useCase
    }
}