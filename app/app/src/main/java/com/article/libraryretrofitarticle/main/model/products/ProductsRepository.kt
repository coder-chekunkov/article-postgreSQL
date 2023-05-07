package com.article.libraryretrofitarticle.main.model.products

import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity

interface ProductsRepository {

    suspend fun getAllProducts(accessToken: String): List<ProductEntity>

    suspend fun addNewProductInBasket(accessToken: String, userId: Long, productId: Long)

    suspend fun getAllUserProductInBasket(accessToken: String, userId: Long): List<ProductEntity>
}