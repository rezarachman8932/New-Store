package com.app.store.presentation.splash

import com.app.store.shared.usecase.UserUseCase
import com.app.store.shared.vm.BaseViewModel

class SplashViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    fun isLoggedIn(): Boolean {
        return userUseCase.isLoggedIn()
    }

}