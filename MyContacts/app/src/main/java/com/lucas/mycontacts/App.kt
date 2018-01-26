package com.lucas.mycontacts

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.lucas.mycontacts.di.ApplicationComponent
import com.lucas.mycontacts.di.ApplicationModule
import com.lucas.mycontacts.di.DaggerApplicationComponent
import com.lucas.mycontacts.di.DatabaseModule

/**
 * Created by lucas on 13/11/2017.
 */
class App: MultiDexApplication() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .databaseModule(DatabaseModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}

val Activity.app: App
get() = application as App