package com.article.libraryretrofitarticle.utils

import android.content.Context
import com.article.libraryretrofitarticle.spread.model.entities.TokenEntity

interface TokenService {

    fun setTokens(tokens: TokenEntity)

    fun getAccessToken(): String?

    fun getRefreshToken(): String?
}

class TokenSharedPreferencesService(context: Context) : TokenService {

    private val sharedPreferences =
        context.getSharedPreferences(TOKEN_FILE_NAME, Context.MODE_PRIVATE)

    override fun setTokens(tokens: TokenEntity) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, tokens.accessToken)
        editor.putString(REFRESH_TOKEN, tokens.refreshToken)
        editor.apply()
    }

    override fun getAccessToken(): String? = sharedPreferences.getString(ACCESS_TOKEN, null)
    override fun getRefreshToken(): String? = sharedPreferences.getString(REFRESH_TOKEN, null)

    companion object {
        private const val TOKEN_FILE_NAME = "token_file"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }
}