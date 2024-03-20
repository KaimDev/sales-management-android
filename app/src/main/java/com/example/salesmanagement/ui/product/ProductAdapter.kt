package com.example.salesmanagement.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanagement.R
import com.example.salesmanagement.database.entities.Product

class ProductAdapter(private val tvEmpty: TextView) : RecyclerView.Adapter<ProductAdapter.ProductHolder>()
{
    private var productList = emptyList<Product>()

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder
    {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_holder_row, parent, false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int)
    {
        val currentItem = productList[position]
        holder.itemView.findViewById<TextView>(R.id.tvProductNameValue).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.tvProductTypeValue).text = currentItem.type
        holder.itemView.findViewById<TextView>(R.id.tvProductBrandValue).text = currentItem.brand
        holder.itemView.findViewById<TextView>(R.id.tvProductSizeValue).text = currentItem.size
        holder.itemView.findViewById<TextView>(R.id.tvProductPriceValue).text = currentItem.price.toString()
        holder.itemView.findViewById<TextView>(R.id.tvProductQuantityValue).text = currentItem.quantity.toString()
        holder.itemView.findViewById<TextView>(R.id.tvProductDescriptionValue).text = currentItem.description
        holder.itemView.findViewById<TextView>(R.id.tvProductColorValue).text = currentItem.color
    }

    override fun getItemCount(): Int = productList.size

    fun setData(products: List<Product>)
    {
        productList = products
        notifyDataSetChanged()

        handleEmptyView()
    }

    private fun handleEmptyView()
    {
        if (productList.isEmpty())
        {
            tvEmpty.visibility = View.VISIBLE
        }
        else
        {
            tvEmpty.visibility = View.GONE
        }
    }
}