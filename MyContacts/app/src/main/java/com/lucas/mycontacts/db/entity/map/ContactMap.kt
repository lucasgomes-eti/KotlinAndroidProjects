package com.lucas.mycontacts.db.entity.map

import com.lucas.mycontacts.db.entity.ContactEntity
import com.lucas.mycontacts.model.Contact

/**
 * Created by lucas on 13/11/2017.
 */
fun Contact.map() : ContactEntity {
    return ContactEntity(
            id,
            firstName,
            lastName,
            phoneNumber,
            email
    )
}