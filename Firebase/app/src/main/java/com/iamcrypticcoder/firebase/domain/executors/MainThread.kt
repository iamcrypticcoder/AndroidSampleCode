package com.iamcrypticcoder.firebase.domain.executors

/**
 * This interface will define a class that will enable use cases to run specific operations in the
 * main (UI) thread. For example, if an use case needs to show an object to the UI this can be used
 * to make sure the show method is called on the UI thread.
 */
interface MainThread {
    fun post(runnable: Runnable)
}