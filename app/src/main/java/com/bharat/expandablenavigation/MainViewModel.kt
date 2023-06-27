package com.bharat.expandablenavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _toolbarText: MutableLiveData<String> = MutableLiveData("")
    val toolbarText: LiveData<String>
        get() = _toolbarText

    fun setToolBarText(toolbarTitle: String) {
        _toolbarText.postValue(toolbarTitle)
    }
}