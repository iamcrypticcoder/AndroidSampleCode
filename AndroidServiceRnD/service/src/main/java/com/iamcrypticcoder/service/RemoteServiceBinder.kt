package com.iamcrypticcoder.service

import android.R.attr.delay
import android.os.RemoteException
import android.text.TextUtils
import android.util.Log
import com.iamcrypticcoder.service.aidl.Company
import com.iamcrypticcoder.service.aidl.Employee
import java.util.*
import java.util.concurrent.ConcurrentHashMap

private const val LONG_TASK_START_DELAY = 1000L
private const val LONG_TASK_PROGRESS_DELAY = 1000L

object RemoteServiceBinder : IRemoteService.Stub() {
    private val TAG = RemoteServiceBinder::class.java.simpleName

    // Not worked. Crash
//    val mListenerList: RemoteCallbackList<IRemoteServiceListener> =
//        RemoteCallbackList<IRemoteServiceListener>()

    private val mListenerMap: ConcurrentHashMap<String, IRemoteServiceListener> =
        ConcurrentHashMap<String, IRemoteServiceListener>()

    private var demoProgressValue: Int = 0
    private lateinit var demoTaskId: String

    override fun getPid(): Int {
        return -1;
    }

    override fun registerListener(listener: IRemoteServiceListener?) {
        Log.d(TAG, "registerListener()")
        if (null != listener) {
            //mListenerList.register(listener)
            mListenerMap.put(listener.hashCode().toString(), listener)
        }
    }

    override fun unregisterListener(listener: IRemoteServiceListener?) {
        Log.d(TAG, "unregisterListener()")
        if (null != listener) {
            mListenerMap.remove(listener.hashCode().toString())
        }
    }

    override fun createCompany(company: Company?) {
        Log.d(TAG, "createCompany()")
        if (null == company)
            return
        FakeDB.createCompany(company)

        for (listener in mListenerMap.entries) {
            try {
                listener.value.onCompanyCreated(company)
            } catch (e: RemoteException) {
                Log.d(TAG, "Remote Exception")
                e.printStackTrace()
            }
        }

        // Broadcast to all clients the new value.
//        val n = mListenerList.beginBroadcast()
//        for (i in 0 until n) {
//            try {
//                mListenerList.getBroadcastItem(i).onCompanyCreated(company)
//            } catch (e: RemoteException) { }
//        }
//        mListenerList.finishBroadcast()
    }

    override fun addEmployee(companyName: String?, employee: Employee?) {
        if (TextUtils.isEmpty(companyName) || null == employee)
            return
        FakeDB.addEmployee(companyName!!, employee)

        for (listener in mListenerMap.entries) {
            try {
                listener.value.onEmployeeAdded(employee)
            } catch (e: RemoteException) {
                Log.d(TAG, "Remote Exception")
                e.printStackTrace()
            }
        }

        // Broadcast to all clients the new value.
//        val n = mListenerList.beginBroadcast()
//        for (i in 0 until n) {
//            try {
//                mListenerList.getBroadcastItem(i).onEmployeeAdded(employee)
//            } catch (e: RemoteException) { }
//        }
//        mListenerList.finishBroadcast()
    }

    override fun removeEmployee(companyName: String?, employee: Employee?) {
        if (TextUtils.isEmpty(companyName) || null == employee)
            return
        FakeDB.removeEmployee(companyName!!, employee)

        for (listener in mListenerMap.entries) {
            try {
                listener.value.onEmployeeRemoved(employee)
            } catch (e: RemoteException) {
                Log.d(TAG, "Remote Exception")
                e.printStackTrace()
            }
        }

        // Broadcast to all clients the new value.
//        val n = mListenerList.beginBroadcast()
//        for (i in 0 until n) {
//            try {
//                mListenerList.getBroadcastItem(i).onEmployeeRemoved(employee)
//            } catch (e: RemoteException) { }
//        }
//        mListenerList.finishBroadcast()
    }

    override fun getCompany(companyName: String?): Company? {
        if (TextUtils.isEmpty(companyName))
            return null
        return FakeDB.getCompany(companyName!!)
    }

    override fun doLongTask(taskId: String?) {
        demoProgressValue = 0
        demoTaskId = taskId ?: ""

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Log.d(TAG, "run() - " + demoProgressValue)
                if (demoProgressValue == 0) {
                    notifyLongTaskStarted();
                }
                if (demoProgressValue == 100) {
                    notifyLongTaskCompleted();
                    timer.cancel()
                    return
                }
                demoProgressValue += 5;
                notifyLongTaskProgress((demoProgressValue / 100.0).toFloat())
            }
        }, LONG_TASK_START_DELAY, LONG_TASK_PROGRESS_DELAY)
    }

    private fun notifyLongTaskStarted() {
        for (listener in mListenerMap.entries) {
            try {
                listener.value.onLongTaskStarted(demoTaskId)
            } catch (e: RemoteException) {
                Log.d(TAG, "Remote Exception")
                e.printStackTrace()
            }
        }
    }

    private fun notifyLongTaskProgress(progress: Float) {
        for (listener in mListenerMap.entries) {
            try {
                listener.value.onLongTaskInProgress(demoTaskId, progress)
            } catch (e: RemoteException) {
                Log.d(TAG, "Remote Exception")
                e.printStackTrace()
            }
        }
    }

    private fun notifyLongTaskCompleted() {
        for (listener in mListenerMap.entries) {
            try {
                listener.value.onLongTaskCompleted(demoTaskId)
            } catch (e: RemoteException) {
                Log.d(TAG, "Remote Exception")
                e.printStackTrace()
            }
        }
    }
}