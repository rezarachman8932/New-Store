package com.app.store.di

import com.app.store.data.domain.QuoteService
import com.app.store.data.domain.UserService
import com.app.store.data.local.SharedPref
import com.app.store.data.repository.QuoteRepository
import com.app.store.data.repository.UserRepository
import com.app.store.data.service.APIService
import com.app.store.presentation.landing.fragment.home.HomeViewModel
import com.app.store.presentation.landing.fragment.list_quotes.QuoteListViewModel
import com.app.store.presentation.landing.fragment.quote_day.QuoteOfTheDayViewModel
import com.app.store.presentation.landing.fragment.quote_detail.QuoteDetailViewModel
import com.app.store.presentation.splash.SplashViewModel
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
    viewModel { QuoteListViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { QuoteOfTheDayViewModel(get()) }
    viewModel { QuoteDetailViewModel(get()) }
}

val appComponent: List<Module> = listOf(
    singleton,
    localModule,
    networkModule,
    dataSourceModule,
    viewModelModule,
    useCaseModule
)