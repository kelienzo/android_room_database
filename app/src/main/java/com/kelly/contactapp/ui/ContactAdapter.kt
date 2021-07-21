package com.kelly.contactapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelly.contactapp.databinding.ContactListBinding
import com.kelly.contactapp.models.Contact

class ContactAdapter(var contacts: List<Contact>, val listener: OnContactItemClickListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(private val binding: ContactListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun data(contact: Contact) {
            binding.apply {
                tvId.text = contact.id.toString()
                tvFirstName.text = contact.firstName
                tvLastName.text = contact.lastName
                tvCellNumber.text = contact.cellNumber
                root.setOnClickListener {
                    listener.onItemClick(contact)
                }
                imageDelete.setOnClickListener {
                    listener.onItemDelete(contact)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding: ContactListBinding =
            ContactListBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.data(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    interface OnContactItemClickListener {
        fun onItemClick(contact: Contact)
        fun onItemDelete(contact: Contact)
    }
}