package com.iamcrypticcoder.androidservice.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iamcrypticcoder.androidservice.R
import com.iamcrypticcoder.androidservice.databinding.ActivityBindingBinding
import com.iamcrypticcoder.androidservice.service.*
import com.iamcrypticcoder.androidservice.service.LocalService.LocalBinder


/**
 * https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/app/LocalServiceActivities.java
 */
class BindingActivity : AppCompatActivity() {
    private val TAG = BindingActivity::class.java.simpleName
    private lateinit var binding: ActivityBindingBinding

    // Local Service
    private var mLocalServiceBound = false
    private var mLocalService: LocalService? = null

    // Local Messenger Service
    private var mLocalMessengerService: Messenger? = null
    private var mLocalMessengerServiceBound = false

    private val mLocalServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mLocalService = (service as LocalBinder).getService()
            Toast.makeText(this@BindingActivity, R.string.local_service_connected, Toast.LENGTH_SHORT).show()
            binding.randomNumberButton1.isEnabled = true;
            binding.doLongTask1.isEnabled = true
        }
        override fun onServiceDisconnected(className: ComponentName) {
            Toast.makeText(this@BindingActivity, R.string.local_service_disconnected, Toast.LENGTH_SHORT).show()
            binding.randomNumberButton1.isEnabled = false;
            binding.doLongTask1.isEnabled = false
        }
    }

    private val mLocalMessengerServiceConnection : ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.d(TAG, "mLocalMessengerServiceConnection - onServiceConnected()")
            mLocalMessengerService = Messenger(service)
            Toast.makeText(this@BindingActivity, "Local messenger service connected", Toast.LENGTH_SHORT).show()
            binding.randomNumberButton2.isEnabled = true;
            binding.doLongTask2.isEnabled = true

            try {
                var msg = Message.obtain(null, MSG_REGISTER_CLIENT)
                msg.replyTo = mMessenger;
                mLocalMessengerService!!.send(msg)

                // Give it some value as an example.
                msg = Message.obtain(null, MSG_SET_VALUE, hashCode(), 0)
                mLocalMessengerService!!.send(msg)
            } catch (e : RemoteException) {
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            }
        }
        override fun onServiceDisconnected(className: ComponentName) {
            Log.d(TAG, "mLocalMessengerServiceConnection - onServiceDisconnected()")
            mLocalMessengerService = null
            Toast.makeText(this@BindingActivity, "Local messenger service disconnected", Toast.LENGTH_SHORT).show()
            binding.randomNumberButton2.isEnabled = false;
            binding.doLongTask2.isEnabled = false
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    val mMessenger = Messenger(IncomingHandler())

    /**
     * Handler of incoming messages from service.
     */
    inner class IncomingHandler : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            Log.d(TAG, "IncomingHandler - handleMessage() - " + msg.what)
            when (msg.what) {
                MSG_SET_VALUE -> {
                    Toast.makeText(this@BindingActivity, "Value from service = " + msg.arg1, Toast.LENGTH_SHORT).show()
                }
                MSG_GEN_RANDOM -> {
                    Toast.makeText(this@BindingActivity, "Random value from service = " + msg.arg1, Toast.LENGTH_SHORT).show()
                }
                else ->
                    super.handleMessage(msg)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLocalServiceDemo();
        setupLocalMessengerServiceDemo();
    }

    private fun setupLocalServiceDemo() {
        binding.bindButton.setOnClickListener {
            bindLocalService()
        }
        binding.unbindButton.setOnClickListener {
            unbindLocalService()
        }
        binding.randomNumberButton1.setOnClickListener {
            if (false == mLocalServiceBound) {
                Toast.makeText(this@BindingActivity, "Local Service is not bound", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }
            var x = mLocalService!!.generateRandomNumber()
            Toast.makeText(this@BindingActivity, "Random value from service = " + x.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.doLongTask1.setOnClickListener {

        }
    }

    private fun setupLocalMessengerServiceDemo() {
        binding.bindButton2.setOnClickListener {
            bindLocalMessengerService()
        }
        binding.unbindButton2.setOnClickListener {
            unbindLocalMessengerService()
        }
        binding.randomNumberButton2.setOnClickListener {
            if (false == mLocalMessengerServiceBound) {
                Toast.makeText(this@BindingActivity, "Local Messenger Service is not bound", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            var msg = Message.obtain(null, MSG_GEN_RANDOM)
            msg.replyTo = mMessenger;
            mLocalMessengerService!!.send(msg)
        }
        binding.doLongTask2.setOnClickListener {

        }
    }

    private fun bindLocalService() {
        val bindingResult = bindService(
                Intent(this, LocalService::class.java),
                mLocalServiceConnection,
                Context.BIND_AUTO_CREATE
        )
        if (bindingResult) {
            mLocalServiceBound = true;
        } else {
            Log.e(TAG, "Error: The requested service doesn't exist, or this client isn't allowed access to it.");
        }
    }

    private fun unbindLocalService() {
        if (mLocalServiceBound) {
            unbindService(mLocalServiceConnection)
            mLocalServiceBound = false
        }
    }

    private fun bindLocalMessengerService() {
        bindService(
                Intent(this, LocalMessengerService::class.java),
                mLocalMessengerServiceConnection,
                Context.BIND_AUTO_CREATE)
        mLocalMessengerServiceBound = true;

    }

    private fun unbindLocalMessengerService() {
        if (false == mLocalMessengerServiceBound) {
            Log.e(TAG, "Local Messenger service not binded");
            return
        }

        try {
            var msg = Message.obtain(null, MSG_UNREGISTER_CLIENT)
            msg.replyTo = mMessenger
            mLocalMessengerService!!.send(msg)
        } catch (e : RemoteException) {
            // There is nothing special we need to do if the service
            // has crashed.
        }

        unbindService(mLocalMessengerServiceConnection)
        mLocalMessengerServiceBound = false
        Toast.makeText(this@BindingActivity, "Unbinding...", Toast.LENGTH_SHORT).show()
    }

}