package com.article.libraryretrofitarticle.main.model.user

import com.article.libraryretrofitarticle.main.model.user.entities.EditPersonalDataEntity
import com.article.libraryretrofitarticle.main.model.user.entities.PersonalDataEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserAPI {

    @GET("users/{user_id}")
    suspend fun getUserPersonalData(
        @Header("Authorization") accessToken: String,
        @Path("user_id") userId: Long
    ): PersonalDataEntity

    @PATCH("users/edit/{user_id}")
    suspend fun editUserPersonalData(
        @Header("Authorization") accessToken: String,
        @Path("user_id") userId: Long,
        @Body newUserData: EditPersonalDataEntity
    ): PersonalDataEntity
}