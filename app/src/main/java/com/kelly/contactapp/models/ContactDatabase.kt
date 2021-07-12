package com.kelly.contactapp.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            INSTANCE?.let {
                return it
            } ?: kotlin.run {
                INSTANCE =
                    Room.databaseBuilder(context, ContactDatabase::class.java, "contact_database")
                        .allowMainThreadQueries().build()
                return INSTANCE!!
            }
        }
    }
}