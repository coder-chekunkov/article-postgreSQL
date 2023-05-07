package com.article.libraryretrofitarticle.main.model.user

import com.article.libraryretrofitarticle.main.model.user.entities.EditPersonalDataEntity
import com.article.libraryretrofitarticle.main.model.user.entities.PersonalDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class UserServerRepository(retrofit: Retrofit) : UserRepository {

    private val userAPI = retrofit.create(UserAPI::class.java)

    override suspend fun getPersonalData(accessToken: String, userId: Long): PersonalDataEntity {
        return withContext(Dispatchers.IO) {
            return@withContext userAPI.getUserPersonalData("Bearer $accessToken", userId)
        }
    }

    override suspend fun editPersonalData(
        accessToken: String, firstName: String, lastName: String?, middleName: String?, userId: Long
    ): PersonalDataEntity {
        return withContext(Dispatchers.IO) {
            val newUserData = EditPersonalDataEntity(
                firstName = firstName, lastName = lastName, middleName = middleName
            )

            return@withContext userAPI.editUserPersonalData(
                accessToken = "Bearer $accessToken", userId = userId, newUserData = newUserData
            )
        }
    }
}