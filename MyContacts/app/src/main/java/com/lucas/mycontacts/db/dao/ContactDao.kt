package com.lucas.mycontacts.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.lucas.mycontacts.db.entity.ContactEntity

/**
 * Created by lucas on 13/11/2017.
 */
@Dao
interface ContactDao {
    @Insert(onConflict = REPLACE)
    fun createContact(contact: ContactEntity)
    @Query("SELECT * FROM contact")
    fun readContacts(): LiveData<List<ContactEntity>>
    @Query("SELECT * FROM contact WHERE id = :id")
    fun readContactById(id: Int): ContactEntity?
    @Update
    fun updateContact(contact: ContactEntity)
    @Delete
    fun deleteContact(contact: ContactEntity)
}