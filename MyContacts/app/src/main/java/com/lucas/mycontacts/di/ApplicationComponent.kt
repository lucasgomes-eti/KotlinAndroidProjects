package com.lucas.mycontacts.di

import com.lucas.mycontacts.App
import com.lucas.mycontacts.ui.ContactsActivity
import com.lucas.mycontacts.ui.NewContactActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lucas on 13/11/2017.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DatabaseModule::class))
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(activity: ContactsActivity)
    fun inject(activity: NewContactActivity)
}