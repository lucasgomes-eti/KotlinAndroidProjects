package com.lucas.mycontacts.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lucas.mycontacts.R
import com.lucas.mycontacts.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

/**
 * Created by lucas on 13/11/2017.
 */
class ContactsAdapter(var contacts: MutableList<Contact>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_contact, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ContactsViewHolder) {
            val contact = contacts?.get(position) ?: return
            holder.name.text = "${contact.firstName} ${contact.lastName}"
        }
    }

    override fun getItemCount() = contacts?.size ?: 0

    private class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView by lazy { itemView.findViewById<TextView>(R.id.txtName) }
    }
}