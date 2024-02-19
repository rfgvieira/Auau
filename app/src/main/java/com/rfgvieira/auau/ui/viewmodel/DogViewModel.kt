package com.rfgvieira.auau.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rfgvieira.auau.domain.Dog
import com.rfgvieira.auau.domain.Dogs

class DogViewModel : ViewModel() {
    private val _uriImage = MutableLiveData(Uri.EMPTY)
    val uriImage : MutableLiveData<Uri> get() =  _uriImage

    val dogList = Dogs.dogsList()

    fun changeUri(uri : Uri){
        _uriImage.postValue(uri)
    }

    fun deleteDog(dog: Dog){
        dogList.remove(dog)
    }

}