package com.article.libraryretrofitarticle.main.model.products.entities

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("price") var price: Double,
    @SerializedName("image") var image: String
)
