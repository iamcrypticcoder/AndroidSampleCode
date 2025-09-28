package com.iamcrypticcoder.contactaccess.ui.repository

import contacts.core.entities.Contact

interface ContactRepository {
    suspend fun getAllContacts() : List<Contact>
}