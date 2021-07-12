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
            tvFirst.text = intent.getStringExtra("first_name")
            tvLast.text = intent.getStringExtra("last_name")
            tvNumber.text = intent.getStringExtra("cell_number")
        }
    }
}