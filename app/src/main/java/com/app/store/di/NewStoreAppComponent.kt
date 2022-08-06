package com.app.store.di

import com.app.store.data.local.SharedPref
import org.koin.core.module.Module
import org.koin.dsl.module

val singleton = module {
    single { SharedPref(get()) }
}

val localModule = module {

}

val networkModule = module {

}

val dataSourceModule = module {

}

val useCaseModule = module {

}

val viewModelModule = module {

}

val appComponent: List<Module> = listOf(
    singleton,
    localModule,
    networkModule,
    dataSourceModule,
    viewModelModule,
    useCaseModule
)