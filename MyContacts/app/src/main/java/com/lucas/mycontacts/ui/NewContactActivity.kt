package com.lucas.mycontacts.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lucas.mycontacts.R
import com.lucas.mycontacts.app
import com.lucas.mycontacts.model.Contact
import com.lucas.mycontacts.viewmodel.NewContactViewModel
import kotlinx.android.synthetic.main.activity_new_contact.*
import java.util.*
import javax.inject.Inject

class NewContactActivity : AppCompatActivity() {

    @Inject lateinit var newContactViewModel: NewContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)
        app.component.inject(this)

        btnSaveContact.setOnClickListener {
            saveContact()
        }
    }

    private fun saveContact() {
        newContactViewModel.saveContact(Contact(Random().nextInt(), ietFirstName.editText?.text.toString(), ietLastName.editText?.text.toString(), ietPhoneNumber.editText?.text.toString(), ietEmail.editText?.text.toString()))
    }
}
