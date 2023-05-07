package com.article.libraryretrofitarticle.main.screens.basket

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.GridLayoutManager
import com.article.core.utils.collectFlow
import com.article.core.views.BaseFragment
import com.article.core.views.HasCustomTitle
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentMainBasketBinding
import com.article.libraryretrofitarticle.databinding.FragmentMainShopBinding
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity
import com.article.libraryretrofitarticle.main.screens.shop.ProductsAdapter

class BasketFragment : BaseFragment(R.layout.fragment_main_basket), HasCustomTitle {

    override val viewModel: BasketViewModel by screenViewModel()
    private lateinit var binding: FragmentMainBasketBinding
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBasketBinding.bind(view)
        mView = view

        collectFlow(viewModel.productsInBasket) { setupRecyclerView(it) }
        viewModel.getAllUserProducts(mView)
    }

    private fun setupRecyclerView(allProducts: List<ProductEntity>) {
        val adapter = BasketAdapter()
        adapter.data = allProducts
        setupLayoutManager(binding, adapter)
    }

    private fun setupLayoutManager(
        binding: FragmentMainBasketBinding,
        adapter: BasketAdapter
    ) {
        if (isAdded) {
            binding.recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    val width = binding.recyclerView.width
                    val itemWidth = requireContext().resources.getDimensionPixelSize(R.dimen.item_width_product)
                    val columns = width / itemWidth
                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), columns)
                }
            })
        }
    }

    override fun getScreenTitle(): String = getString(R.string.basket_title)
}