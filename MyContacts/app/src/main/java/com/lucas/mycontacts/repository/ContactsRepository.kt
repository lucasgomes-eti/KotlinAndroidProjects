package com.lucas.mycontacts.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.lucas.mycontacts.db.dao.ContactDao
import com.lucas.mycontacts.model.Contact
import javax.inject.Inject

/**
 * Created by lucas on 14/11/2017.
 */
class ContactsRepository @Inject constructor(private val contactDao: ContactDao) {

    val contacts: LiveData<List<Contact>>
    get() = Transformations.map(contactDao.readContacts()){it.map { it.map() }}

}