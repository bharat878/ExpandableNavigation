package com.bharat.expandablenavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bharat.expandablenavigation.utils.Constants

class MainViewModel: ViewModel() {

    private val _toolbarText: MutableLiveData<String> = MutableLiveData("")
    val toolbarText: LiveData<String>
        get() = _toolbarText

    init {
        _toolbarText.postValue(Constants.APP_NAME)
    }
    fun setToolBarText(toolbarTitle: String) {
        _toolbarText.postValue(toolbarTitle)
    }
}