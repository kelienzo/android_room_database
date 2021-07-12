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
            addContact()?.let { Contact -> database.contactDao().addContact(Contact) }
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun inputCheck(): Boolean {
        return binding.textInputFirstName.editText?.text.toString().trim().isNotEmpty() &&
                binding.textInputLastName.editText?.text.toString().trim().isNotEmpty() &&
                binding.textInputNumber.editText?.text.toString().trim().isNotEmpty()
    }

    private fun addContact(): Contact? {
        if (inputCheck()) {
            return Contact(
                id = 0,
                firstName = binding.textInputFirstName.editText?.text.toString(),
                lastName = binding.textInputLastName.editText?.text.toString(),
                cellNumber = binding.textInputNumber.editText?.text.toString()
            )
        }
        return null
    }
}