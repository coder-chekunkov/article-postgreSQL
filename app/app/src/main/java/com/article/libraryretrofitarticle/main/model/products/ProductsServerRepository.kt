package com.article.libraryretrofitarticle.main.model.products

import com.article.libraryretrofitarticle.main.model.products.entities.NewProductEntity
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class ProductsServerRepository(retrofit: Retrofit) : ProductsRepository {

    private val productsAPI = retrofit.create(ProductsAPI::class.java)

    override suspend fun getAllProducts(accessToken: String): List<ProductEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext productsAPI.getAllProducts("Bearer $accessToken")
        }
    }

    override suspend fun addNewProductInBasket(accessToken: String, userId: Long, productId: Long) {
        withContext(Dispatchers.IO) {
            productsAPI.addNewProductInBasket(
                accessToken = "Bearer $accessToken",
                body = NewProductEntity(
                    userId = userId,
                    productId = productId
                )
            )
        }
    }

    override suspend fun getAllUserProductInBasket(accessToken: String, userId: Long): List<ProductEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext productsAPI.getAllUserProductInBasket(
                accessToken = "Bearer $accessToken",
                userId = userId
            )
        }
    }
}