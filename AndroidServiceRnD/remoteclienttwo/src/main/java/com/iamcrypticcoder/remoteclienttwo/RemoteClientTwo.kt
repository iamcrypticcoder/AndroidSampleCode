package com.iamcrypticcoder.remoteclienttwo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.service.IRemoteService
import com.iamcrypticcoder.service.IRemoteServiceListener
import com.iamcrypticcoder.service.aidl.Company
import com.iamcrypticcoder.service.aidl.Employee
import kotlinx.android.synthetic.main.activity_remote_client_two.*


class RemoteClientTwo : AppCompatActivity() {
    private val TAG = RemoteClientTwo::class.java.simpleName

    var mService: IRemoteService? = null

    private var isBound: Boolean = false

    val serviceListener = object : IRemoteServiceListener.Stub() {
        override fun onCompanyCreated(company: Company?) {
            Log.d(TAG, "onCompanyCreated()")
            runOnUiThread({
                messageTextView.text = "Company created = " + company!!.name
            })
        }

        override fun onEmployeeAdded(employee: Employee?) {
            runOnUiThread({
                messageTextView.text = "Company added = " + employee!!.name
            })
        }

        override fun onEmployeeRemoved(employee: Employee?) {
            runOnUiThread({
                messageTextView.text = "Employee removed = " + employee!!.name
            })
        }

        override fun onLongTaskStarted(taskId: String?) {
            runOnUiThread({
                messageTextView.text = "Long Task started.\n Task Id = " + taskId
            })
        }

        override fun onLongTaskInProgress(taskId: String?, progress: Float) {
            runOnUiThread({
                messageTextView.text = "Long Task in progress.\n Task Id = " + taskId + "\nProgress = " + progress*100
            })
        }

        override fun onLongTaskCompleted(taskId: String?) {
            runOnUiThread({
                messageTextView.text = "Long Task completed.\n Task Id = " + taskId
            })
        }
    }

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            messageTextView.text = "Remote service connected"
            //Toast.makeText(this@RemoteClientOne, "Remote service connected", Toast.LENGTH_SHORT).show()
            mService = IRemoteService.Stub.asInterface(service)
            mService?.registerListener(serviceListener)

            // Enable UI
            enableButtons(true)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            messageTextView.text = "Remote service disconnected"
            //Toast.makeText(this@RemoteClientOne, "Remote service disconnected", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Service has unexpectedly disconnected")
            mService = null

            // Enable UI
            enableButtons(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_client_two)
        setupUI();
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        unbindService()
        super.onDestroy()
    }

    private fun setupUI() {
        textView1.text = "Remote Client 2"
        bindButton.setOnClickListener {
            bindService()
        }
        unbindButton.setOnClickListener {
            unbindService()
        }
        addCompanyButton.setOnClickListener {
            mService?.createCompany(Company("My Company 2"))
        }
        addEmployeeButton.setOnClickListener {
            mService?.addEmployee("My Company 2", Employee(100, "Mahbub", 30, 10000, "Software Enginner"))
        }
    }

    private fun bindService() {
        // Notice that remoteclient one actually host the service.
        // remoteclient two is truly remote client
        val intent = Intent("com.iamcrypticcoder.service.IRemoteService")
        intent.setPackage("com.iamcrypticcoder.remoteclientone")
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        isBound = true
    }

    private fun unbindService() {
        if (isBound) {
            mService?.unregisterListener(serviceListener)
            unbindService(mConnection)
            isBound = false
            messageTextView.text = "Unbinded"
        }
    }

    private fun enableButtons(enable : Boolean) {
        // Enable UI
        addCompanyButton.isEnabled = enable
        addEmployeeButton.isEnabled = enable
        removeEmployeeButton.isEnabled = enable
        longTaskButton.isEnabled = enable
    }
}