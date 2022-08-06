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

class UserLoginViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private var _userCreateSession = MutableLiveData<ResultState<UserCreateSession>>()
    var userCreateSession: LiveData<ResultState<UserCreateSession>> = _userCreateSession

    fun createSession(request: UserCreateRequest) {
        loaderState.postValue(LoaderState.OnLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userUseCase.createSession(request)

                if (response.isSuccessful) {
                    response.body()?.userToken?.let { userUseCase.setToken(it) }
                    _userCreateSession.postValue(ResultState.Success(data = response.body()))
                } else {
                    _userCreateSession.postValue(
                        ResultState.Error(
                        errorCode = response.code(),
                        data = response.errorBody()
                    ))
                }

                loaderState.postValue(LoaderState.OnLoading(false))
            } catch (throwable: Throwable) {
                _userCreateSession.postValue(ResultState.Error(exception = throwable))

                loaderState.postValue(LoaderState.OnLoading(false))
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