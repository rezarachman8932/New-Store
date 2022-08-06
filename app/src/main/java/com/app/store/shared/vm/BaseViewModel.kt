package com.app.store.shared.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.store.shared.model.LoaderState
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {

    protected var loaderState = MutableLiveData<LoaderState>()
    val loader : LiveData<LoaderState>
        get() = loaderState

}