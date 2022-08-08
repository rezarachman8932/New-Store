# New-Store

This application is built using MVVM architecture. There are four major layers inside it.
- Presentation. It consists of activity, fragment or other views that present UI.
- ViewModel. I can put all business logic functionalities here (API call, validation, mapper, etc). 
- UseCase. This kind of class will bridge between view-model and repository layer. I can independently and simply call the use-case class without knowing the implementation inside it.
- Repository. All we need from our data sources can be put in here (data persistence, local data and API).


# How

To store all instances that have application scope, i defined them inside app-component class. It's important to make it sorted based on their dependencies each other.

```
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
    viewModel { QuoteListTagSearchViewModel(get()) }
}

val appComponent: List<Module> = listOf(
    singleton,
    localModule,
    networkModule,
    dataSourceModule,
    viewModelModule,
    useCaseModule
)
```


For API instance, you can find in APIService class. This class contains any functions that supports API builder needs.

```
inline fun <reified I> create(sharedPreferences: SharedPref) : I {
        return builder(BASE_URL, sharedPreferences)
    }

    inline fun <reified I> builder(url: String, sharedPreferences: SharedPref) : I {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(getOKHttp(sharedPreferences))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(I::class.java)
    }
```

# What's Inside

- Retrofit
- Koin Component
- Moshi
- Coroutine
- Navigation Component
- RxJava
- Kotlinx Synthetic


# TO-DO

- Unit test
- Show loading progress
- Error handling
- Standardize data class
- Some views improvements
