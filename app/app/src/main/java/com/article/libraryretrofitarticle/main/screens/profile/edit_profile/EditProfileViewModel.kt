package com.article.libraryretrofitarticle.main.screens.profile.edit_profile

import android.view.View
import androidx.lifecycle.viewModelScope
import com.article.core.uiactions.UiActions
import com.article.core.views.BaseViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.main.model.user.UserRepository
import com.article.libraryretrofitarticle.utils.TokenService
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val uiActions: UiActions,
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) : BaseViewModel() {

    fun saveNewData(view: View, firstName: String, lastName: String?, middleName: String?) {
        viewModelScope.launch {
            try {
                val accessToken = tokenService.getAccessToken()!!
                val jwt = JWT(accessToken)
                val id = jwt.getClaim("id").asString()?.toLong() ?: -1

                userRepository.editPersonalData(
                    accessToken = accessToken,
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    userId = id
                )

                showSnackbar(view, uiActions.getString(R.string.successful_edit_profile_data))
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