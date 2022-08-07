package com.app.store.presentation.landing.fragment.list_quotes.tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.store.data.model.QuoteListResponse
import com.app.store.shared.usecase.QuoteUseCase
import com.app.store.shared.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteListTagSearchViewModel(private val quoteUseCase: QuoteUseCase) : BaseViewModel() {

    private var _quoteTagSearchList = MutableLiveData<QuoteListResponse>()
    var quoteTagSearchList: LiveData<QuoteListResponse> = _quoteTagSearchList

    fun getQuoteTagSearchList(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteUseCase.getListQuotesByTag(tag)
                if (response.isSuccessful) {
                    _quoteTagSearchList.postValue(response.body())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

}