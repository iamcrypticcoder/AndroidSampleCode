package com.example.constraintlayoutdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = ChainSpreadFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spreadDemoButton.setOnClickListener {
            openSecondActivity(ChainSpreadFragment::class.java)
        }
        spreadInsideDemoButton.setOnClickListener {
            openSecondActivity(ChainSpreadInsideFragment::class.java)
        }
        packedDemoButton.setOnClickListener {
            openSecondActivity(ChainPackedFragment::class.java)
        }
        weightedDemoButton.setOnClickListener {
            openSecondActivity(ChainWeightedFragment::class.java)
        }
        resizableRecyclerDemoButton.setOnClickListener {
            openSecondActivity(ResizableRecyclerViewFragment::class.java)
        }
    }


    private fun openSecondActivity(fragmentClass: Class<*>) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(Const.FRAGMENT_CLASS_NAME, fragmentClass.name)
        startActivity(intent)
    }
}