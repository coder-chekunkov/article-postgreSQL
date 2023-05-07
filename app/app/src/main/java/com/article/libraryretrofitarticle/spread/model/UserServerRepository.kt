package com.article.libraryretrofitarticle.spread.model

import com.article.libraryretrofitarticle.spread.model.entities.AuthEntity
import com.article.libraryretrofitarticle.spread.model.entities.RegistrationEntity
import com.article.libraryretrofitarticle.spread.model.entities.TokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class UserServerRepository(retrofit: Retrofit) : UserRepository {

    private val userAPI = retrofit.create(UserAPI::class.java)

    override suspend fun signIn(email: String, password: String): TokenEntity {
        return withContext(Dispatchers.IO) {
            val authEntity = AuthEntity(email, password)

            return@withContext userAPI.authorization(authEntity)
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String?,
        middleName: String?
    ): TokenEntity {
        return withContext(Dispatchers.IO) {
            val registrationEntity = RegistrationEntity(email, password, firstName, lastName, middleName)

            return@withContext userAPI.registration(registrationEntity)
        }
    }
}
