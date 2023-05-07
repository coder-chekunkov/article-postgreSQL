package com.article.libraryretrofitarticle.spread.model

import com.article.libraryretrofitarticle.spread.model.entities.TokenEntity

interface UserRepository {

    suspend fun signIn(email: String, password: String): TokenEntity

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String? = null,
        middleName: String? = null
    ): TokenEntity

}