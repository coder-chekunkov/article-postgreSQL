package com.article.libraryretrofitarticle.spread.screens.home

import androidx.fragment.app.Fragment
import com.article.core.navigator.Navigator
import com.article.core.views.BaseViewModel

class HomeViewModel(
    private val navigator: Navigator
): BaseViewModel() {

    fun launchAuthScreen(fragment: Fragment) {
        val directions = HomeFragmentDirections.actionHomeFragmentToAuthFragment()
        navigator.launchByTopNavController(fragment, directions)
    }

    fun launchRegistration(fragment: Fragment) {
        val directions = HomeFragmentDirections.actionHomeFragmentToRegistrationFragment()
        navigator.launchByTopNavController(fragment, directions)
    }

}