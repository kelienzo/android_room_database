package com.kelly.contactapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact_table ORDER BY id ASC")
    fun getAllContacts(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}