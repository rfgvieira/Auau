package com.rfgvieira.auau.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogViewModel : ViewModel() {
    private val _uriImage = MutableLiveData(Uri.EMPTY)
    val uriImage : MutableLiveData<Uri> get() =  _uriImage

    fun changeUri(uri : Uri){
        _uriImage.postValue(uri)
    }

}