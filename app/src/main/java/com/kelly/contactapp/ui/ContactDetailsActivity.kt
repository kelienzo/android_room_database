package com.kelly.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.kelly.contactapp.MainActivity
import com.kelly.contactapp.databinding.ActivityContactDetailsBinding
import com.kelly.contactapp.models.Contact
import com.kelly.contactapp.models.ContactDatabase
import com.kelly.contactapp.models.ContactViewModel

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var database: ContactDatabase
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        database = ContactDatabase.getDatabase(this)
        contact = Contact(
            intent.getIntExtra("id", 0),
            intent.getStringExtra("first_name")!!,
            intent.getStringExtra("last_name")!!,
            intent.getStringExtra("cell_number")!!
        )

        binding.apply {
            textInputUpdateFirstName.editText?.setText(contact.firstName)
            textInputUpdateLastName.editText?.setText(contact.lastName)
            textInputUpdatePhoneNumber.editText?.setText(contact.cellNumber)
        }
        binding.apply {
            btnUpdate.setOnClickListener {
                updateContact()
            }
            ivDelete.setOnClickListener {
                deleteContact()
            }
        }
    }

    private fun deleteContact() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("yes") { _, _ ->
            viewModel.deleteContact(database, contact)
            Toast.makeText(
                this,
                "Successfully removed: ${contact.firstName}",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${contact.firstName}?")
        builder.setMessage("Are you sure you want to delete ${contact.firstName}")
        builder.create().show()
    }

    private fun updateContact() {
        val firstName = binding.textInputUpdateFirstName.editText?.text.toString()
        val lastName = binding.textInputUpdateLastName.editText?.text.toString()
        val cellNumber = binding.textInputUpdatePhoneNumber.editText?.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && cellNumber.isNotEmpty()) {

            val updatedContact =
                Contact(contact.id, firstName, lastName, cellNumber)
            // Updates the current contact
            viewModel.updateContact(database, updatedContact)

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_LONG).show()
            // Navigate back
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
}