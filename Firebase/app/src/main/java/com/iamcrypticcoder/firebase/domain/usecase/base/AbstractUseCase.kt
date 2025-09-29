package com.iamcrypticcoder.firebase.domain.usecase.base

import androidx.annotation.MainThread
import java.util.concurrent.Executor

abstract class AbstractUseCase(
    protected val threadExecutors: Executor,
    protected val mainThread: MainThread
) : UseCase {

}