package com.kelly.contactapp.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    val contactsLiveData = MutableLiveData<List<Contact>>()

    fun getContact(database: ContactDatabase) {
        contactsLiveData.postValue(database.contactDao().getAllContacts())
    }

    fun saveContact(database: ContactDatabase, contact: Contact) {
        database.contactDao().addContact(contact)
        getContact(database)
    }

    fun updateContact(database: ContactDatabase, contact: Contact) {
        database.contactDao().updateContact(contact)
        getContact(database)
    }

    fun deleteContact(database: ContactDatabase, contact: Contact) {
        database.contactDao().deleteContact(contact)
        getContact(database)
    }
}