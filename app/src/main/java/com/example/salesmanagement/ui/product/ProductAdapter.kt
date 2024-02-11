package com.example.salesmanagement.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanagement.models.Product
import com.example.salesmanagement.R

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder
    {
        val layoutInflate = LayoutInflater.from(parent.context)
        return ProductHolder(layoutInflate.inflate(R.layout.product_view, parent, false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int)
    {
        holder.render(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}