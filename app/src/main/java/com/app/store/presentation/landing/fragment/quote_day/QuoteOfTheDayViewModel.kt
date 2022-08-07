package com.app.store.presentation.landing.fragment.quote_day

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteOfTheDayResponse
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteOfTheDayViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteOfTheDay = MutableLiveData<QuoteOfTheDayResponse>()
    var quoteOfTheDay: LiveData<QuoteOfTheDayResponse> = _quoteOfTheDay

    fun getQuoteOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getQuoteOfTheDay()

                if (response.isSuccessful) {
                    _quoteOfTheDay.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }
}