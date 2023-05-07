package com.article.libraryretrofitarticle.main.model.products

import com.article.libraryretrofitarticle.main.model.products.entities.NewProductEntity
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsAPI {

    @GET("products")
    suspend fun getAllProducts(@Header("Authorization") accessToken: String): List<ProductEntity>

    @POST("carts/add")
    suspend fun addNewProductInBasket(
        @Header("Authorization") accessToken: String,
        @Body body: NewProductEntity
    )

    @GET("carts/{user_id}")
    suspend fun getAllUserProductInBasket(
        @Header("Authorization") accessToken: String,
        @Path("user_id") userId: Long
    ): List<ProductEntity>

}