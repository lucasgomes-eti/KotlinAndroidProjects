package com.lucas.mycontacts.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lucas.mycontacts.db.dao.ContactDao
import com.lucas.mycontacts.db.entity.ContactEntity

/**
 * Created by lucas on 13/11/2017.
 */
@Database(entities = arrayOf(ContactEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
        abstract fun contactDao(): ContactDao
}