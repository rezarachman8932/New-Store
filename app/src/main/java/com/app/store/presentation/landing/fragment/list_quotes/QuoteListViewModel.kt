package com.app.store.presentation.landing.fragment.list_quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteListResponse
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteListViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteList = MutableLiveData<QuoteListResponse>()
    var quoteList: LiveData<QuoteListResponse> = _quoteList

    fun getQuoteList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getListQuotes()
                if (response.isSuccessful) {
                    _quoteList.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

}