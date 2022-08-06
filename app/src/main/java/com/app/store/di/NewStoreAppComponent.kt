package com.app.store.di

import com.app.store.data.domain.QuoteService
import com.app.store.data.domain.UserService
import com.app.store.data.local.SharedPref
import com.app.store.data.repository.QuoteRepository
import com.app.store.data.repository.UserRepository
import com.app.store.data.service.APIService
import com.app.store.presentation.user.UserLoginViewModel
import com.app.store.presentation.user.UserRegistrationViewModel
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.usecase.UserUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val singleton = module {
    single { SharedPref(get()) }
}

val localModule = module {

}

val networkModule = module {
    single { APIService.create<UserService>(get()) }
    single { APIService.create<QuoteService>(get()) }
}

val dataSourceModule = module {
    single { UserRepository(get(), get()) }
    single { QuoteRepository(get()) }
}

val useCaseModule = module {
    single { QuoteUseCase(get()) }
    single { UserUseCase(get()) }
}

val viewModelModule = module {
    viewModel { UserRegistrationViewModel(get()) }
    viewModel { UserLoginViewModel(get()) }
}

val appComponent: List<Module> = listOf(
    singleton,
    localModule,
    networkModule,
    dataSourceModule,
    viewModelModule,
    useCaseModule
)