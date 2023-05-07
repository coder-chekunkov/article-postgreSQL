package com.article.libraryretrofitarticle.main.screens.shop

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.GridLayoutManager
import com.article.core.utils.collectFlow
import com.article.core.views.BaseFragment
import com.article.core.views.HasCustomTitle
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentMainShopBinding
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity

class ShopFragment : BaseFragment(R.layout.fragment_main_shop), HasCustomTitle {

    override val viewModel: ShopViewModel by screenViewModel()
    private lateinit var binding: FragmentMainShopBinding
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainShopBinding.bind(view)
        mView = view

        collectFlow(viewModel.allProducts) { setupRecyclerView(it) }
        viewModel.getAllProducts(mView)
    }

    private fun setupRecyclerView(allProducts: List<ProductEntity>) {
        val adapter = ProductsAdapter(itemProductListener)
        adapter.data = allProducts
        setupLayoutManager(binding, adapter)
    }

    private fun setupLayoutManager(
        binding: FragmentMainShopBinding,
        adapter: ProductsAdapter
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

    private val itemProductListener = object : ItemProductListener {
        override fun onItemProductPressed(productId: Long, productName: String) =
            viewModel.addProductInBasket(
                view = mView,
                productId = productId,
                message = getString(R.string.main_shop_product_added, productName)
            )
    }

    override fun getScreenTitle(): String = getString(R.string.shop_title)
}