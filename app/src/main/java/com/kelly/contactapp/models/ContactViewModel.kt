package com.kelly.contactapp.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    val contactsLiveData = MutableLiveData<List<Contact>>()

    fun getContacts(database: ContactDatabase) {
        contactsLiveData.postValue(database.contactDao().getAllContacts())
    }

    fun addContact(database: ContactDatabase, contact: Contact) {
        database.contactDao().addContact(contact)
        getContacts(database)
    }

    fun updateContact(database: ContactDatabase, contact: Contact) {
        database.contactDao().updateContact(contact)
        getContacts(database)
    }

    fun deleteContact(database: ContactDatabase, contact: Contact) {
        database.contactDao().deleteContact(contact)
        getContacts(database)
    }
}