package com.lucas.mycontacts.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.lucas.mycontacts.model.Contact

/**
 * Created by lucas on 13/11/2017.
 */
@Entity(tableName = "contact")
        class ContactEntity(
        @PrimaryKey
        var id: Int,
        var firstName: String,
        var lastName: String,
        var phoneNumber: String,
        var email: String
) {
    fun map(): Contact {
        return Contact(
                id,
                firstName,
                lastName,
                phoneNumber,
                email
        )
    }
}