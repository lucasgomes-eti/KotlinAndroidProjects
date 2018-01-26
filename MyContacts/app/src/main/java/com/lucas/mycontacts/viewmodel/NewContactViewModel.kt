package com.lucas.mycontacts.viewmodel

import com.lucas.mycontacts.model.Contact
import com.lucas.mycontacts.repository.NewContactRepository
import javax.inject.Inject

/**
 * Created by lucas on 14/11/2017.
 */
class NewContactViewModel @Inject constructor(private val newContactRepository: NewContactRepository) {

    fun saveContact(contact: Contact) = newContactRepository.saveContact(contact)
}