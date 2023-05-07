package com.article.libraryretrofitarticle.spread.screens.registration

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.article.core.navigator.Navigator
import com.article.core.uiactions.UiActions
import com.article.core.views.BaseViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.main.MainActivity
import com.article.libraryretrofitarticle.spread.model.UserRepository
import com.article.libraryretrofitarticle.utils.TokenService
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) : BaseViewModel() {

    fun launchAuthScreen(fragment: Fragment) {
        val directions = RegistrationFragmentDirections.actionRegistrationFragmentToAuthFragment()
        navigator.launchByTopNavController(fragment, directions)
    }

    private fun launchMainScreen() {
        navigator.launchNewActivity(MainActivity())
    }

    fun registration(
        view: View,
        email: String,
        password: String,
        firstName: String,
        lastName: String? = null,
        middleName: String? = null
    ) {
        viewModelScope.launch {
            try {
                val tokenEntity = userRepository.signUp(email, password, firstName, lastName, middleName)
                tokenService.setTokens(tokenEntity)

                showSnackbar(view, uiActions.getString(R.string.successful_registration))
                launchMainScreen()
            } catch (exception: Exception) {
                showSnackbar(view, uiActions.getString(R.string.unknown_error_registration))
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