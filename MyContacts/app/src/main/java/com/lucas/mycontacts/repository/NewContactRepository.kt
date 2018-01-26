package com.lucas.mycontacts.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.lucas.mycontacts.db.dao.ContactDao
import com.lucas.mycontacts.db.entity.map.map
import com.lucas.mycontacts.model.Contact
import javax.inject.Inject

/**
 * Created by lucas on 14/11/2017.
 */
class NewContactRepository @Inject constructor(private val contactDao: ContactDao) {

    fun saveContact(contact: Contact){
        contactDao.createContact(contact.map())
    }
}