package com.lucas.mycontacts.di

import android.arch.persistence.room.Room
import android.content.Context
import com.lucas.mycontacts.db.AppDatabase
import com.lucas.mycontacts.db.dao.ContactDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lucas on 13/11/2017.
 */
@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @Singleton
    fun providesDatabase() = Room
            .databaseBuilder(context, AppDatabase::class.java, "mycontacts.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun providesContactDao(db: AppDatabase): ContactDao = db.contactDao()
}