package com.kelly.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kelly.contactapp.R
import com.kelly.contactapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            textInputUpdateFirstName.editText?.setText(intent.getStringExtra("first_name"))
            textInputUpdateLastName.editText?.setText(intent.getStringExtra("last_name"))
            textInputUpdatePhoneNumber.editText?.setText(intent.getStringExtra("cell_number"))
        }

    }
}