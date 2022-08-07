package com.app.store.presentation.landing.fragment.list_quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteListResponse
import com.app.store.shared.model.BaseResponse
import com.app.store.shared.model.LoaderState
import com.app.store.shared.model.ResultState
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteListViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteList = MutableLiveData<ResultState<BaseResponse<QuoteListResponse>>>()
    var quoteList: LiveData<ResultState<BaseResponse<QuoteListResponse>>> = _quoteList

    fun getQuoteList() {
        loaderState.postValue(LoaderState.OnLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getListQuotes()

                if (response.isSuccessful) {
                    _quoteList.postValue(ResultState.Success(data = response.body()))
                } else {
                    _quoteList.postValue(ResultState.Error(
                        errorCode = response.code(),
                        data = response.errorBody()
                    ))
                }

                loaderState.postValue(LoaderState.OnLoading(false))
            } catch (throwable: Throwable) {
                _quoteList.postValue(ResultState.Error(exception = throwable))

                loaderState.postValue(LoaderState.OnLoading(false))
            }
        }
    }

}