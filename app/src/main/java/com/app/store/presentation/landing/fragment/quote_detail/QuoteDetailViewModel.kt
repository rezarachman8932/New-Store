package com.app.store.presentation.landing.fragment.quote_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteOfTheDayDetail
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteDetailViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteDetail = MutableLiveData<QuoteOfTheDayDetail>()
    var quoteDetail: LiveData<QuoteOfTheDayDetail> = _quoteDetail

    fun getQuoteDetail(quoteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getQuote(quoteId)
                if (response.isSuccessful) {
                    _quoteDetail.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

}