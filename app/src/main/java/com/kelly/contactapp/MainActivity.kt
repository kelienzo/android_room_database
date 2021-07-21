package com.kelly.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.kelly.contactapp.databinding.ActivityMainBinding
import com.kelly.contactapp.models.Contact
import com.kelly.contactapp.models.ContactDatabase
import com.kelly.contactapp.models.ContactViewModel
import com.kelly.contactapp.ui.ContactAdapter
import com.kelly.contactapp.ui.ContactDetailsActivity
import com.kelly.contactapp.ui.SaveContactActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var viewModel: ContactViewModel
    private lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = ContactDatabase.getDatabase(this)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        viewModel.getContact(database)

        contactAdapter = ContactAdapter(arrayListOf(), listener)
        binding.contactRv.adapter = contactAdapter

        binding.btnAddNew.setOnClickListener {
            val intent = Intent(this, SaveContactActivity::class.java)
            startActivity(intent)
        }

        //observe live data from viewModel
        viewModel.contactsLiveData.observe(this, { contacts ->
            contactAdapter.contacts = contacts
            contactAdapter.notifyDataSetChanged()
        })
    }

    private val listener = object : ContactAdapter.OnContactItemClickListener {
        override fun onItemClick(contact: Contact) {
            val intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
            intent.putExtra("first_name", contact.firstName)
            intent.putExtra("last_name", contact.lastName)
            intent.putExtra("cell_number", contact.cellNumber)
            startActivity(intent)
        }

        override fun onItemDelete(contact: Contact) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setPositiveButton("yes") { _, _ ->
                viewModel.deleteContact(database, contact)
                Toast.makeText(
                    this@MainActivity,
                    "Successfully removed: ${contact.firstName}",
                    Toast.LENGTH_LONG
                ).show()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${contact.firstName}?")
            builder.setMessage("Are you sure you want to delete ${contact.firstName}")
            builder.create().show()
        }
    }
}