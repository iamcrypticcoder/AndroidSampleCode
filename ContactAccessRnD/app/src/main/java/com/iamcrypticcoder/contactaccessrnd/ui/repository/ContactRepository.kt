package com.iamcrypticcoder.contactaccessrnd.ui.repository

import contacts.core.entities.Contact

interface ContactRepository {
    suspend fun getAllContacts() : List<Contact>
}