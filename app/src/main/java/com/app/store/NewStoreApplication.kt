package com.app.store

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.app.store.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class NewStoreApplication : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidContext(this@NewStoreApplication)
            modules(appComponent)
        }
    }

}