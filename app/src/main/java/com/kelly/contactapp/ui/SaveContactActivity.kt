package com.kelly.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.kelly.contactapp.MainActivity
import com.kelly.contactapp.databinding.ActivitySaveContactBinding
import com.kelly.contactapp.models.Contact
import com.kelly.contactapp.models.ContactDatabase
import com.kelly.contactapp.models.ContactViewModel

class SaveContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveContactBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var database: ContactDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        database = ContactDatabase.getDatabase(this)

        binding.btnSave.setOnClickListener {
            addContact()
        }
    }

    private fun addContact() {
        val firstname = binding.textInputFirstName.editText?.text.toString()
        val lastName = binding.textInputLastName.editText?.text.toString()
        val cellNumber = binding.textInputNumber.editText?.text.toString()

        if (firstname.isNotEmpty() && lastName.isNotEmpty() && cellNumber.isNotEmpty()) {

            val saveContact =
                Contact(id = 0, firstname, lastName, cellNumber)
            // Add contact to database
            viewModel.addContact(database, saveContact)

            Toast.makeText(this, "Successfully Added", Toast.LENGTH_LONG).show()
            // Navigate back
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
}