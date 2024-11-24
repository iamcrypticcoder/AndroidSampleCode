package com.iamcrypticcoder.contactaccessrnd.ui.repository

import android.content.Context
import contacts.core.Contacts
import contacts.core.entities.Contact

class DeviceContactRepository(context: Context) : ContactRepository {

    private val appContext: Context = context

    override suspend fun getAllContacts(): List<Contact> {
        val contacts = Contacts(context = appContext).query().find()
        val list = mutableListOf<Contact>()
        contacts.forEach {
            list.add(it)
        }
        return list
    }
}