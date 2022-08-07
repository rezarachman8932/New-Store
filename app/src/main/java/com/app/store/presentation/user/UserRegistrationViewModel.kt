package com.app.store.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.UserCreateRequest
import com.app.store.data.model.UserCreateSession
import com.app.store.shared.usecase.UserUseCase
import com.app.store.shared.validation.UserValidation
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private var _userCreate = MutableLiveData<UserCreateSession>()
    var userCreate: LiveData<UserCreateSession> = _userCreate

    fun createUser(request: UserCreateRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userUseCase.createUser(request)
                if (response.isSuccessful) {
                    userUseCase.setToken("")
                    _userCreate.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

    fun ableToRegister(
        login: String,
        email: String,
        password: String
    ): Boolean {
        val validUsername = UserValidation.isCorrectName(login)
        val validEmail = UserValidation.isValidEmail(email)
        val validPassword = UserValidation.isValidPassword(password)

        return validUsername && validEmail && validPassword
    }

}