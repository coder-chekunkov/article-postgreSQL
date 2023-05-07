package com.article.libraryretrofitarticle.spread.model

import com.article.libraryretrofitarticle.spread.model.entities.AuthEntity
import com.article.libraryretrofitarticle.spread.model.entities.RegistrationEntity
import com.article.libraryretrofitarticle.spread.model.entities.TokenEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("auth/login")
    suspend fun authorization(@Body body: AuthEntity): TokenEntity

    @POST("auth/register")
    suspend fun registration(@Body body: RegistrationEntity) : TokenEntity
}