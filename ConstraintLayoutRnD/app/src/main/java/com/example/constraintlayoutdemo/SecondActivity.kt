package com.example.constraintlayoutdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SecondActivity : AppCompatActivity() {
    private val TAG = ChainSpreadFragment::class.java.simpleName

    private var mFragmentClassName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mFragmentClassName = intent.getStringExtra(Const.FRAGMENT_CLASS_NAME)

        showFragment()
    }

    private fun showFragment() {
        try {
            var mFragment = Class.forName(mFragmentClassName).newInstance() as Fragment

            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentContainer, mFragment)
            try {
                ft.commit()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                ft.commitAllowingStateLoss()
            }
            ft.show(mFragment)
        } catch (e: IllegalStateException) {
            Log.d(TAG, "IllegalStateException " + e.message)
            e.printStackTrace()
        }
    }
}