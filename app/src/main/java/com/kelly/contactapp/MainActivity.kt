package com.kelly.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        contactAdapter = ContactAdapter(arrayListOf(), listener)
        binding.contactRv.adapter = contactAdapter

        //observe live data from viewModel
        viewModel.getContacts(database)
        viewModel.contactsLiveData.observe(this, { contacts ->
            contactAdapter.contacts = contacts
            contactAdapter.notifyDataSetChanged()
        })

        binding.floatingActionBtnAddNew.setOnClickListener {
            val intent = Intent(this, SaveContactActivity::class.java)
            startActivity(intent)
        }
    }

    private val listener = object : ContactAdapter.OnContactItemClickListener {
        override fun onItemClick(contact: Contact) {
            val intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
            intent.putExtra("id", contact.id)
            intent.putExtra("first_name", contact.firstName)
            intent.putExtra("last_name", contact.lastName)
            intent.putExtra("cell_number", contact.cellNumber)
            startActivity(intent)
        }
    }
}