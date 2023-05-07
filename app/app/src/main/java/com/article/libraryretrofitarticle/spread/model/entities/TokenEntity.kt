package com.article.libraryretrofitarticle.spread.model.entities

import com.google.gson.annotations.SerializedName

data class TokenEntity(
    @SerializedName("accessToken") var accessToken: String? = null,
    @SerializedName("refreshToken") var refreshToken: String? = null
)