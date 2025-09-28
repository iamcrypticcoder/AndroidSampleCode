package com.iamcrypticcoder.androidpermission

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_network_permission.*

class NetworkPermissionActivity : AppCompatActivity() {
    private val TAG = NetworkPermissionActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_permission)

        getConnectionType(this)
    }

    fun getConnectionType(context: Context) {
        var isWifiConn = false
        var isMobileConn = false
        var isVPNConn = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        isWifiConn = true
                    }
                    if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        isMobileConn = true
                    }
                    if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        isVPNConn = true
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isWifiConn = true
                    }
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                        isMobileConn = true
                    }
                    if(type == ConnectivityManager.TYPE_VPN) {
                        isVPNConn = true
                    }
                }
            }
        }

        Log.d(TAG, "Wifi connected: $isWifiConn")
        Log.d(TAG, "Mobile connected: $isMobileConn")
        Log.d(TAG, "VPN connected: $isVPNConn")

        textView.text = "Wifi connected: $isWifiConn\n" +
                "Mobile connected: $isMobileConn\n" +
                "VPN connected: $isVPNConn"
    }
}