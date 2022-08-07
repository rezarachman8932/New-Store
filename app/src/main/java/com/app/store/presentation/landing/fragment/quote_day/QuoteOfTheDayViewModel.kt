package com.app.store.presentation.landing.fragment.quote_day

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteOfTheDayResponse
import com.app.store.shared.model.BaseResponse
import com.app.store.shared.model.LoaderState
import com.app.store.shared.model.ResultState
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteOfTheDayViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteOfTheDay = MutableLiveData<ResultState<BaseResponse<QuoteOfTheDayResponse>>>()
    var quoteOfTheDay: LiveData<ResultState<BaseResponse<QuoteOfTheDayResponse>>> = _quoteOfTheDay

    fun getQuoteOfTheDay() {
        loaderState.postValue(LoaderState.OnLoading(true))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getQuoteOfTheDay()

                if (response.isSuccessful) {
                    _quoteOfTheDay.postValue(ResultState.Success(data = response.body()))
                } else {
                    _quoteOfTheDay.postValue(ResultState.Error(
                        errorCode = response.code(),
                        data = response.errorBody()
                    ))
                }

                loaderState.postValue(LoaderState.OnLoading(false))
            } catch (throwable: Throwable) {
                _quoteOfTheDay.postValue(ResultState.Error(exception = throwable))

                loaderState.postValue(LoaderState.OnLoading(false))
            }
        }
    }
}