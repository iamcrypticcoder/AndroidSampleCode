package com.example.constraintlayoutdemo

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import kotlin.random.Random

object Util {
    val TAG = Util::class.java.simpleName

    fun getRandomSampleImageRes(activity : Activity) : Int {
        val index = Random(System.nanoTime()).nextInt(1, 16)
        Log.d(TAG, "getRandomSampleImageRes() : index = " + index);
        return activity.resources.getIdentifier("sample_image_$index", "drawable", activity.packageName)
    }

    fun getGridItemWidthInPx(
            context: Context,
            horizontalSideMargin: Int,
            spanCount : Int
    ) : Int {
        val availableWidth = context.resources.displayMetrics.widthPixels - (2 * horizontalSideMargin)
        return (availableWidth / spanCount)
    }

    fun isRTLMode(context: Context?): Boolean {
        context?.let {
            val config = it.resources.configuration
            return config.layoutDirection == View.LAYOUT_DIRECTION_RTL
        } ?: return false
    }
}