package com.app.store.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.UserCreateRequest
import com.app.store.data.model.UserCreateSession
import com.app.store.shared.model.LoaderState
import com.app.store.shared.model.ResultState
import com.app.store.shared.usecase.UserUseCase
import com.app.store.shared.validation.UserValidation
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private var _userCreate = MutableLiveData<ResultState<UserCreateSession>>()
    var userCreate: LiveData<ResultState<UserCreateSession>> = _userCreate

    fun createUser(request: UserCreateRequest) {
        loaderState.postValue(LoaderState.OnLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userUseCase.createUser(request)

                if (response.isSuccessful) {
                    response.body()?.userToken?.let { userUseCase.setToken(it) }
                    _userCreate.postValue(ResultState.Success(data = response.body()))
                } else {
                    _userCreate.postValue(ResultState.Error(
                        errorCode = response.code(),
                        data = response.errorBody()
                    ))
                }

                loaderState.postValue(LoaderState.OnLoading(false))
            } catch (throwable: Throwable) {
                _userCreate.postValue(ResultState.Error(exception = throwable))

                loaderState.postValue(LoaderState.OnLoading(false))
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