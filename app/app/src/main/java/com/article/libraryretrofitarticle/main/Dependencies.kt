package com.article.libraryretrofitarticle.main

import android.content.Context
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.main.model.products.ProductsRepository
import com.article.libraryretrofitarticle.main.model.products.ProductsServerRepository
import com.article.libraryretrofitarticle.main.model.user.UserRepository
import com.article.libraryretrofitarticle.main.model.user.UserServerRepository
import com.article.libraryretrofitarticle.utils.TokenService
import com.article.libraryretrofitarticle.utils.TokenSharedPreferencesService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies {

    private lateinit var applicationContext: Context

    fun initDependencies(context: Context) {
        applicationContext = context
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(applicationContext.getString(R.string.server))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()
    }

    val userRepository: UserRepository by lazy { UserServerRepository(retrofit) }
    val productsRepository: ProductsRepository by lazy { ProductsServerRepository(retrofit) }
    val tokenService: TokenService by lazy { TokenSharedPreferencesService(applicationContext) }
}