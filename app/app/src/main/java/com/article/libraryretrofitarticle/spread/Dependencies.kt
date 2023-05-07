package com.article.libraryretrofitarticle.spread

import android.content.Context
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.spread.model.UserRepository
import com.article.libraryretrofitarticle.spread.model.UserServerRepository
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
    val tokenService: TokenService by lazy { TokenSharedPreferencesService(applicationContext) }
}