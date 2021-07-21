package com.kelly.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.kelly.contactapp.MainActivity
import com.kelly.contactapp.R
import com.kelly.contactapp.databinding.ActivityContactDetailsBinding
import com.kelly.contactapp.models.Contact
import com.kelly.contactapp.models.ContactDatabase
import com.kelly.contactapp.models.ContactViewModel

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        database = ContactDatabase.getDatabase(this)

        binding.apply {
            textInputUpdateFirstName.editText?.setText(intent.getStringExtra("first_name"))
            textInputUpdateLastName.editText?.setText(intent.getStringExtra("last_name"))
            textInputUpdatePhoneNumber.editText?.setText(intent.getStringExtra("cell_number"))
        }

        binding.btnUpdate.setOnClickListener {
            updateContact()
        }
    }

    private fun updateContact() {
        val firstName = binding.textInputUpdateFirstName.editText?.text.toString()
        val lastName = binding.textInputUpdateLastName.editText?.text.toString()
        val cellNumber = binding.textInputUpdatePhoneNumber.editText?.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && cellNumber.isNotEmpty()) {

            val contact = Contact(id = 0, firstName, lastName, cellNumber)
            viewModel.updateContact(database, contact)

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
}