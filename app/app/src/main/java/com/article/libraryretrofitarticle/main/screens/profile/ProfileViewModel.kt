package com.article.libraryretrofitarticle.main.screens.profile

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.article.core.navigator.Navigator
import com.article.core.uiactions.UiActions
import com.article.core.views.BaseViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.main.model.user.UserRepository
import com.article.libraryretrofitarticle.main.model.user.entities.PersonalDataEntity
import com.article.libraryretrofitarticle.utils.TokenService
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) : BaseViewModel() {

    private val _personalData = MutableStateFlow(PersonalDataEntity.emptyPersonalDataEntity)
    val personalData: StateFlow<PersonalDataEntity> = _personalData

    fun launchEditPersonalDataScreen(fragment: Fragment) {
        val directions = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(_personalData.value)
        val navController = fragment.findNavController()
        navigator.launchByNavController(navController, directions)
    }

    fun getPersonalData(view: View) {
        viewModelScope.launch {
            try {
                val accessToken = tokenService.getAccessToken()!!
                val jwt = JWT(accessToken)
                val id = jwt.getClaim("id").asString()?.toLong() ?: -1

                val personalDataEntity = userRepository.getPersonalData(accessToken, id)
                _personalData.value = personalDataEntity
            } catch (exception: Exception) {
                showSnackbar(view, uiActions.getString(R.string.unknown_error))
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