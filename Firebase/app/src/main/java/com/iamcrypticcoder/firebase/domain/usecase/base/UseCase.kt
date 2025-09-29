package com.iamcrypticcoder.firebase.domain.usecase.base

/**
 * This is the main interface of an interactor. Each interactor serves a specific use case.
 */
interface UseCase {

    /**
     * This is the main method that starts an use case. It will make sure that the interactor
     * operation will be executed on background thread.
     */
    fun execute()
}