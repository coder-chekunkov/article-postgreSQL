package com.article.libraryretrofitarticle.main.screens.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.ItemProductShopBinding
import com.article.libraryretrofitarticle.main.model.products.entities.ProductEntity
import com.bumptech.glide.Glide

interface ItemProductListener {
    fun onItemProductPressed(productId: Long, productName: String)
}

class ProductsAdapter(
    private val itemProductListener: ItemProductListener
) : RecyclerView.Adapter<ProductsAdapter.ItemProductShopViewHolder>(), View.OnClickListener {

    var data: List<ProductEntity> = emptyList()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductShopViewHolder {
        val inflater = LayoutInflater.from(parent.context) as LayoutInflater
        val binding = ItemProductShopBinding.inflate(inflater, parent, false)

        return ItemProductShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemProductShopViewHolder, position: Int) {
        val item = data[position]
        val context = holder.binding.root.context
        holder.binding.root.setOnClickListener(this@ProductsAdapter)
        holder.binding.root.tag = item

        with(holder.binding) {
            productNameTextView.text = item.name
            productPriceTextView.text = context.getString(R.string.main_shop_product_price, item.price.toString())

            Glide.with(context).load(item.image).placeholder(R.drawable.ic_loading).into(productImage)
        }
    }

    class ItemProductShopViewHolder(val binding: ItemProductShopBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val product = view.tag as ProductEntity

        itemProductListener.onItemProductPressed(product.id, product.name)
    }
}