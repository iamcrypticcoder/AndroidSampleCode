package com.iamcrypticcoder.contactaccessrnd.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iamcrypticcoder.contactaccessrnd.databinding.ActivityMainBinding
import com.iamcrypticcoder.contactaccessrnd.ui.viewmodel.ContactViewModel
import contacts.core.Contacts
import contacts.core.util.emailList
import contacts.core.util.emails
import contacts.core.util.organizationList
import contacts.core.util.organizations
import contacts.core.util.phoneList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        GlobalScope.launch {
            contactViewModel.myStateFlow.collect {
                Log.d(TAG, it)
            }
        }

        contactViewModel.updateUIState("New Value")

        viewBinding.accessContactButton.setOnClickListener {
            // Sets the columns to retrieve for the user profile
//            var projection = arrayOf(
//                ContactsContract.Profile._ID,
//                ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
//                ContactsContract.Profile.LOOKUP_KEY,
//                ContactsContract.Profile.PHOTO_THUMBNAIL_URI
//            )
//
//            // Retrieves the profile from the Contacts Provider
//            var profileCursor = contentResolver.query(
//                ContactsContract.Profile.CONTENT_URI,
//                projection,
//                null,
//                null,
//                null
//            )
            //readContacts()
//            val contacts = Contacts(context = applicationContext).query().find()
//            contacts.forEach {
//                Log.d(TAG, "Display Name = ${it.displayNamePrimary}")
//                Log.d(TAG, "Phone List = ${it.phoneList()}")
//                Log.d(TAG, "Emails = ${it.emailList()}")
//                Log.d(TAG, "Work = ${it.organizationList()}")
//            }
//            Log.d(TAG, "Sample Line")
            //simple().forEach { value -> Log.d(TAG, value.toString()) }
            runBlocking {
                simple2().collect { value -> println("Collected $value") }
            }
        }
    }

    fun simple(): Sequence<Int> = sequence { // sequence builder
        for (i in 1..3) {
            Thread.sleep(1000) // pretend we are computing it
            yield(i) // yield next value
        }
    }

    fun simple2(): Flow<Int> = flow { // flow builder
        for (i in 1..3) {
            delay(1000) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }

    @SuppressLint("Range")
    private fun readContacts() {
        val contactList = ArrayList<String>()
        val cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, null, null, null)
        if (cursor?.moveToFirst() == true) {
            do {
                contactList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
                Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
                Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))
                Log.d(TAG, "" + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)))
            } while (cursor?.moveToNext() == true)
        }
        Log.d(TAG, "Total Contact Count = ${contactList.size}")
    }

//    private fun readContact2() {
//        return try {
//            val contacts: ArrayList<Contact> = ArrayList<Contact>()
//            val cr = contentResolver
//            val cursor =
//                cr.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, FILTER, null, ORDER)
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    // get the contact's information
//                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
//                    val name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))
//                    val hasPhone =
//                        cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
//
//                    // get the user's email address
//                    var email: String? = null
//                    val ce = cr.query(
//                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
//                        arrayOf(id),
//                        null
//                    )
//                    if (ce != null && ce.moveToFirst()) {
//                        email =
//                            ce.getString(ce.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
//                        ce.close()
//                    }
//
//                    // get the user's phone number
//                    var phone: String? = null
//                    if (hasPhone > 0) {
//                        val cp = cr.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                            arrayOf(id),
//                            null
//                        )
//                        if (cp != null && cp.moveToFirst()) {
//                            phone =
//                                cp.getString(cp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                            cp.close()
//                        }
//                    }
//
//                    // if the user user has an email or phone then add it to contacts
//                    if ((!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
//                            .matches()
//                                && !email.equals(name, ignoreCase = true)) || !TextUtils.isEmpty(
//                            phone
//                        )
//                    ) {
//                        val contact = Contact()
//                        contact.name = name
//                        contact.email = email
//                        contact.phoneNumber = phone
//                        contacts.add(contact)
//                    }
//                } while (cursor.moveToNext())
//
//                // clean up cursor
//                cursor.close()
//            }
//            contacts
//        } catch (ex: Exception) {
//            null
//        }
//    }

}