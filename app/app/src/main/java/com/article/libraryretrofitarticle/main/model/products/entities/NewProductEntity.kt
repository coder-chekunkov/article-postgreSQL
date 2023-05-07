package com.article.libraryretrofitarticle.main.model.products.entities

import com.google.gson.annotations.SerializedName

data class NewProductEntity(
    @SerializedName("userId") var userId: Long,
    @SerializedName("productId") var productId: Long
)
