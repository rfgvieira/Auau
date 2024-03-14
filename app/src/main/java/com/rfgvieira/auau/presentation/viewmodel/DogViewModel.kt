package com.rfgvieira.auau.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog


class DogViewModel : ViewModel() {
    private val _uriImage = MutableLiveData(Uri.EMPTY)
    val uriImage : MutableLiveData<Uri> get() =  _uriImage

    val dogList = DogDAO.dogsList()

    fun changeUri(uri : Uri){
        _uriImage.postValue(uri)
    }

    fun deleteDog(dog: Dog){
        dogList.remove(dog)
    }

}