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

class UserLoginViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private var _userCreateSession = MutableLiveData<UserCreateSession>()
    var userCreateSession: LiveData<UserCreateSession> = _userCreateSession

    fun createSession(request: UserCreateRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userUseCase.createSession(request)

                if (response.isSuccessful) {
                    userUseCase.setToken("")
                    userUseCase.setLoggedIn(true)
                    userUseCase.setName(response.body()?.login!!)

                    _userCreateSession.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

    fun ableToRegister(
        login: String,
        password: String
    ): Boolean {
        val validUsername = UserValidation.isCorrectName(login)
        val validPassword = UserValidation.isValidPassword(password)

        return validUsername && validPassword
    }

}