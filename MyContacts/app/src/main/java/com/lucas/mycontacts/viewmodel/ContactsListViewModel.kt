package com.lucas.mycontacts.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.lucas.mycontacts.model.Contact
import com.lucas.mycontacts.repository.ContactsRepository
import javax.inject.Inject

/**
 * Created by lucas on 13/11/2017.
 */
class ContactsListViewModel @Inject constructor(private val contactsRepository: ContactsRepository) {

    val contacts: LiveData<List<Contact>>
    get() = contactsRepository.contacts
}