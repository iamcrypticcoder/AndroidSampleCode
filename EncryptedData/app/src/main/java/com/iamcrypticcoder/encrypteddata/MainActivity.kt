package com.iamcrypticcoder.encrypteddata

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

/**
 * https://developer.android.com/topic/security/data#kts
 */
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeToFileButton.setOnClickListener {
            writeFile()
            editText1.setText("")
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
        }
        readFromFileButton.setOnClickListener {
            readFile()
            Toast.makeText(this, "Retrieved", Toast.LENGTH_LONG).show()
        }
    }

    private fun writeFile() {
        // Although you can define your own key generation parameter specification, it's
        // recommended that you use the value specified here.
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        // Create a file with this name, or replace an entire existing file
        // that has the same name. Note that you cannot append to an existing file,
        // and the file name cannot contain path separators.
        val fileToWrite = "my_sensitive_data.txt"
        val file = File(getDir("encrypted_data_folder", MODE_PRIVATE), fileToWrite)
        if (file.exists()) file.delete()

        val encryptedFile = EncryptedFile.Builder(
            file,
            applicationContext,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val fileContent = editText1.text.toString()
            .toByteArray(StandardCharsets.UTF_8)

        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }

    private fun readFile() {
        // Although you can define your own key generation parameter specification, it's
        // recommended that you use the value specified here.
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val fileToRead = "my_sensitive_data.txt"
        val encryptedFile = EncryptedFile.Builder(
            File(getDir("encrypted_data_folder", MODE_PRIVATE), fileToRead),
            applicationContext,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val inputStream = encryptedFile.openFileInput()
        val byteArrayOutputStream = ByteArrayOutputStream()
        var nextByte: Int = inputStream.read()
        while (nextByte != -1) {
            Log.d(TAG, "nextByte = " + nextByte);
            byteArrayOutputStream.write(nextByte)
            nextByte = inputStream.read()
        }

        val plaintext: ByteArray = byteArrayOutputStream.toByteArray()
        val textString = String(plaintext)
        Log.d(TAG, "textString = " + textString)
        editText1.setText(textString)
    }

    private fun writeToSP() {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val sharedPrefsFile: String = "token_info"
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        with (sharedPreferences.edit()) {
            // Edit the user's shared preferences...
            apply()
        }

    }
}