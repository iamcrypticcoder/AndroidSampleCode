package com.iamcrypticcoder.androidpermissionrnd

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camera_permission.*


const val REQUEST_IMAGE_CAPTURE = 1

class CameraPermissionActivity : AppCompatActivity() {
    private val TAG = CameraPermissionActivity::class.java.simpleName

    private lateinit var requestCameraPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_permission)

        registerCameraPermissionLauncher()
        registerCameraLauncher()

        btnOpenCamera.setOnClickListener {
            openCamera()
        }
    }

    private fun registerCameraPermissionLauncher() {
        requestCameraPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    Log.d(TAG, "registerCameraPermission - Camera Permission Granted")
                    openCamera()
                } else {
                    Log.d(TAG, "registerCameraPermission - Camera Permission NOT Granted")
                    //requestCameraPermission()
                }
            }
    }

    private fun registerCameraLauncher() {
        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageBitmap = result?.data?.extras?.get("data") as Bitmap
                    imageView.setImageBitmap(imageBitmap)
                }
            }
    }

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        } else {
            var should = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
            if (should) {
                Log.d(TAG, "requestCameraPermission - Camera Permission NOT Granted")
                showPermissionAlert(
                    getString(R.string.camera_permission),
                    getString(R.string.camera_permission_denied),
                    getString(R.string.ok_caps),
                    getString(R.string.cancel_caps)
                ) { requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA) }
            } else {
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun showPermissionAlert(
        title: String,
        message: String,
        ok: String,
        cancel: String,
        function: () -> Unit
    ) {
        val mDialog = Dialog(this)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.setContentView(R.layout.dialog_permission_alert)
        mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val mTitleTv = mDialog.findViewById<View>(R.id.id_title_tv) as AppCompatTextView
        mTitleTv.text = title

        val mMessageTv = mDialog.findViewById<View>(R.id.id_message_tv) as AppCompatTextView
        mMessageTv.text = message

        val mNoBtn = mDialog.findViewById<View>(R.id.no_btn) as AppCompatTextView
        mNoBtn.text = cancel

        val mYesBtn = mDialog.findViewById<View>(R.id.yes_btn) as AppCompatTextView
        mYesBtn.text = ok

        mYesBtn.setOnClickListener {
            function.invoke()
            mDialog.dismiss()
        }

        mNoBtn.setOnClickListener { mDialog.dismiss() }

        mDialog.setCancelable(true)
        mDialog.show()
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        mDialog.window!!.setLayout(
            width,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    private fun requestCameraPermission() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

}