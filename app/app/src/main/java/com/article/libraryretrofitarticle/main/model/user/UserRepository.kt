package com.article.libraryretrofitarticle.main.model.user

import com.article.libraryretrofitarticle.main.model.user.entities.PersonalDataEntity

interface UserRepository {

    suspend fun getPersonalData(accessToken: String, userId: Long): PersonalDataEntity

    suspend fun editPersonalData(
        accessToken: String,
        firstName: String,
        lastName: String? = null,
        middleName: String? = null,
        userId: Long
    ): PersonalDataEntity
}