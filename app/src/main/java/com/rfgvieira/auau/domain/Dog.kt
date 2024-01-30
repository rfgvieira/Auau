package com.rfgvieira.auau.domain

import android.net.Uri

data class Dog(
    var name : String,
    var birth: String,
    var imgDrawable : Int? = null,
    var imgUri : Uri? = null,
    var favoriteFood : String
)

