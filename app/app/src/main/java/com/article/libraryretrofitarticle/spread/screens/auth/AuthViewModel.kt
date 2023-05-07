package com.article.libraryretrofitarticle.spread.screens.auth

import android.view.View
import androidx.lifecycle.viewModelScope
import com.article.core.navigator.Navigator
import com.article.core.uiactions.UiActions
import com.article.core.views.BaseViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.main.MainActivity
import com.article.libraryretrofitarticle.spread.model.UserRepository
import com.article.libraryretrofitarticle.utils.TokenService
import kotlinx.coroutines.launch

class AuthViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) : BaseViewModel() {

    private fun launchMainScreen() {
        navigator.launchNewActivity(MainActivity())
    }

    fun authorization(view: View, email: String, password: String) {
        viewModelScope.launch() {
            try {
                val tokenEntity = userRepository.signIn(email, password)
                tokenService.setTokens(tokenEntity)

                showSnackbar(view, uiActions.getString(R.string.successful_auth))
                launchMainScreen()
            } catch (exception: Exception) {
                showSnackbar(view, uiActions.getString(R.string.unknown_error_auth))
            }
        }
    }

    private fun showSnackbar(view: View, message: String) = uiActions.showSnackbar(
        view = view,
        message = message,
        backgroundColor = R.color.dark_blue,
        mainColor = R.color.main_color
    )

}