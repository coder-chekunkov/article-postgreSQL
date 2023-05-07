package com.article.libraryretrofitarticle.main.screens.shop

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

class ShopViewModel(
    private val uiActions: UiActions,
    private val productsRepository: ProductsRepository,
    private val tokenService: TokenService
) : BaseViewModel() {

    private val _allProducts = MutableStateFlow(emptyList<ProductEntity>())
    val allProducts: StateFlow<List<ProductEntity>> = _allProducts

    fun getAllProducts(view: View) {
        viewModelScope.launch {
            try {
                val accessToken = tokenService.getAccessToken()!!
                _allProducts.value = productsRepository.getAllProducts(accessToken)
            } catch (exception: Exception) {
                showSnackbar(view, uiActions.getString(R.string.unknown_error))
            }
        }
    }

    fun addProductInBasket(view: View, productId: Long, message: String) {
        viewModelScope.launch {
            try {
                val accessToken = tokenService.getAccessToken()!!
                val jwt = JWT(accessToken)
                val id = jwt.getClaim("id").asString()?.toLong() ?: -1

                productsRepository.addNewProductInBasket(accessToken, id, productId)
                showSnackbar(view, message)
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