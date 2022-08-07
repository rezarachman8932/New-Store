package com.app.store.presentation.landing.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.UserDeleteSessionResponse
import com.app.store.shared.usecase.UserUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val userUseCase: UserUseCase) : BaseViewModel() {

    private var _deleteSession = MutableLiveData<UserDeleteSessionResponse>()
    var deleteSession: LiveData<UserDeleteSessionResponse> = _deleteSession

    fun deleteSession() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = userUseCase.destroySession()
                if (response.isSuccessful) {
                    userUseCase.deleteToken()
                    userUseCase.setLoggedIn(false)
                    userUseCase.deleteName()
                    _deleteSession.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

}