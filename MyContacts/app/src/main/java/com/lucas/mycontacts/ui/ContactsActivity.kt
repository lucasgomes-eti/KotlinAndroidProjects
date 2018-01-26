package com.lucas.mycontacts.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lucas.mycontacts.R
import com.lucas.mycontacts.app
import com.lucas.mycontacts.model.Contact
import com.lucas.mycontacts.viewmodel.ContactsListViewModel
import kotlinx.android.synthetic.main.activity_contacts.*
import org.jetbrains.anko.toast
import java.util.HashSet
import javax.inject.Inject

class ContactsActivity : AppCompatActivity() {

    val contacsAdapter: ContactsAdapter by lazy {
        ContactsAdapter(mutableListOf())
    }

    @Inject lateinit var contactsViewModel: ContactsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        app.component.inject(this)

        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(rcvContacts.context, layoutManager.orientation)

        rcvContacts.layoutManager = layoutManager
        rcvContacts.addItemDecoration(dividerItemDecoration)
        rcvContacts.adapter = contacsAdapter

        fabCreateNewContact.setOnClickListener {
            val intent = Intent(this, NewContactActivity::class.java)
            startActivity(intent)
        }

        contactsViewModel.contacts.observe(this, Observer {
            contacsAdapter.contacts?.clear()
            contacsAdapter.contacts?.addAll(it!!)
            contacsAdapter.notifyDataSetChanged()
        })
    }
}
