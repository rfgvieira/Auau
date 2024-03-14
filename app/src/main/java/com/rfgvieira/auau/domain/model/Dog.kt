package com.rfgvieira.auau.domain.model

import android.net.Uri
import java.util.UUID

data class Dog(
    val id: String = UUID.randomUUID().toString(),
    var name : String,
    var birth: String,
    var imgDrawable : Int? = null,
    var imgUri : Uri? = null,
    var favoriteFood : String? = null,
    var hobbies : List<String>? = null
)

