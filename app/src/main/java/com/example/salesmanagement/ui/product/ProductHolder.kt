package com.example.salesmanagement.ui.product

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanagement.models.Product
import com.example.salesmanagement.R

class ProductHolder(private val view: View) : RecyclerView.ViewHolder(view)
{
    fun render(product: Product)
    {
        view.findViewById<TextView>(R.id.tvProductNameValue).text = product.name
        view.findViewById<TextView>(R.id.tvProductTypeValue).text = product.type
        view.findViewById<TextView>(R.id.tvProductBrandValue).text = product.brand
        view.findViewById<TextView>(R.id.tvProductSizeValue).text = product.size
        view.findViewById<TextView>(R.id.tvProductPriceValue).text = product.price.toString()
        view.findViewById<TextView>(R.id.tvProductQuantityValue).text = product.quantity.toString()
        view.findViewById<TextView>(R.id.tvProductDescriptionValue).text = product.description
        view.findViewById<TextView>(R.id.tvProductColorValue).text = product.color
    }
}