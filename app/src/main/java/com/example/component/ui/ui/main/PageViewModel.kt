package com.example.component.ui.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

//Code generated as part of the tabbed views activity [Android Studio], It forms part of a code boilerplate.
class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = _index.map {
        "This is Tab: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}