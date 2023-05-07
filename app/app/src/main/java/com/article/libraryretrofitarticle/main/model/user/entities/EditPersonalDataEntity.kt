package com.article.libraryretrofitarticle.main.model.user.entities

import com.google.gson.annotations.SerializedName

data class EditPersonalDataEntity(
    @SerializedName("firstName") var firstName: String,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("middleName") var middleName: String? = null
)