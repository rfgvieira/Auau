package com.rfgvieira.auau.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rfgvieira.auau.domain.DogDAO
import com.rfgvieira.auau.domain.model.Dog


class DogViewModel : ViewModel() {
    private val _uriImage = mutableStateOf(Uri.EMPTY)
    val uriImage : MutableState<Uri> get() =  _uriImage

    val dogList = DogDAO.dogsList()

    fun changeUri(uri : Uri){
        _uriImage.value = uri
    }

    fun emptyUri(){
        _uriImage.value =Uri.EMPTY
    }

    fun deleteDog(dog: Dog){
        dogList.remove(dog)
    }
}