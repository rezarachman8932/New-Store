package com.app.store.presentation.landing.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.UserDeleteSessionResponse
import com.app.store.shared.model.LoaderState
import com.app.store.shared.model.ResultState
import com.app.store.shared.usecase.UserUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private var _deleteSession = MutableLiveData<ResultState<UserDeleteSessionResponse>>()
    var deleteSession: LiveData<ResultState<UserDeleteSessionResponse>> = _deleteSession

    fun deleteSession() {
        loaderState.postValue(LoaderState.OnLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userUseCase.destroySession()

                if (response.isSuccessful) {
                    userUseCase.deleteToken()
                    userUseCase.setLoggedIn(false)
                    _deleteSession.postValue(ResultState.Success(data = response.body()))
                } else {
                    _deleteSession.postValue(
                        ResultState.Error(
                            errorCode = response.code(),
                            data = response.errorBody()
                        ))
                }

                loaderState.postValue(LoaderState.OnLoading(false))
            } catch (throwable: Throwable) {
                _deleteSession.postValue(ResultState.Error(exception = throwable))

                loaderState.postValue(LoaderState.OnLoading(false))
            }
        }
    }

}