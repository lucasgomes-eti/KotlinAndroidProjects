package com.lucas.mycontacts.di

import com.lucas.mycontacts.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lucas on 13/11/2017.
 */
@Module
class ApplicationModule(private val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    fun provideContext() = app.applicationContext
}