package com.article.libraryretrofitarticle.spread.model.entities

import com.google.gson.annotations.SerializedName

data class AuthEntity(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)