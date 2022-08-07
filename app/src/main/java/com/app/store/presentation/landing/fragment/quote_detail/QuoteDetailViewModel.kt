package com.app.store.presentation.landing.fragment.quote_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteOfTheDayDetail
import com.app.store.shared.model.BaseResponse
import com.app.store.shared.model.LoaderState
import com.app.store.shared.model.ResultState
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteDetailViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteDetail = MutableLiveData<ResultState<BaseResponse<QuoteOfTheDayDetail>>>()
    var quoteDetail: LiveData<ResultState<BaseResponse<QuoteOfTheDayDetail>>> = _quoteDetail

    fun getQuoteDetail(quoteId: String) {
        loaderState.postValue(LoaderState.OnLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getQuote(quoteId)

                if (response.isSuccessful) {
                    _quoteDetail.postValue(ResultState.Success(data = response.body()))
                } else {
                    _quoteDetail.postValue(
                        ResultState.Error(
                        errorCode = response.code(),
                        data = response.errorBody()
                    ))
                }

                loaderState.postValue(LoaderState.OnLoading(false))
            } catch (throwable: Throwable) {
                _quoteDetail.postValue(ResultState.Error(exception = throwable))

                loaderState.postValue(LoaderState.OnLoading(false))
            }
        }
    }

}