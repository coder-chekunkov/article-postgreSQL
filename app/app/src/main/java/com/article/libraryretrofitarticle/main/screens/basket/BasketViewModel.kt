package com.article.libraryretrofitarticle.main.screens.basket

import android.view.View
import androidx.lifecycle.viewModelScope
import com.article.core.uiactions.UiActions
import com.article.core.views.BaseViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.main.model.products.ProductsRepository
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity
import com.article.libraryretrofitarticle.utils.TokenService
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BasketViewModel(
    private val uiActions: UiActions,
    private val tokenService: TokenService,
    private val productsRepository: ProductsRepository
) : BaseViewModel() {

    private val _productsInBasket = MutableStateFlow(emptyList<ProductEntity>())
    val productsInBasket: StateFlow<List<ProductEntity>> = _productsInBasket

    fun getAllUserProducts(view: View) {
        viewModelScope.launch {
            try {
                val accessToken = tokenService.getAccessToken()!!
                val jwt = JWT(accessToken)
                val id = jwt.getClaim("id").asString()?.toLong() ?: -1

                _productsInBasket.value = productsRepository.getAllUserProductInBasket(accessToken, id)
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