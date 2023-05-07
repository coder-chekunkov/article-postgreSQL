package com.article.libraryretrofitarticle.main.screens.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.ItemProductShopBinding
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity
import com.bumptech.glide.Glide

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.ItemProductBasketViewHolder>() {

    var data: List<ProductEntity> = emptyList()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductBasketViewHolder {
        val inflater = LayoutInflater.from(parent.context) as LayoutInflater
        val binding = ItemProductShopBinding.inflate(inflater, parent, false)

        return ItemProductBasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemProductBasketViewHolder, position: Int) {
        val item = data[position]
        val context = holder.binding.root.context

        with(holder.binding) {
            productNameTextView.text = item.name
            productPriceTextView.text = context.getString(R.string.main_shop_product_price, item.price.toString())

            Glide.with(context).load(item.image).placeholder(R.drawable.ic_loading).into(productImage)
        }
    }

    class ItemProductBasketViewHolder(val binding: ItemProductShopBinding) :
        RecyclerView.ViewHolder(binding.root)
}