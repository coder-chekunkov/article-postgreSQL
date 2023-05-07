package com.article.libraryretrofitarticle.spread.model.entities

import com.google.gson.annotations.SerializedName

data class RegistrationEntity(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("firstName") var firstName: String,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("middleName") var middleName: String? = null
)
