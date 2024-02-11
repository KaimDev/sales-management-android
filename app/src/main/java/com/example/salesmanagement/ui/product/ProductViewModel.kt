package com.example.salesmanagement.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesmanagement.models.Product

class ProductViewModel : ViewModel()
{
    private val _products = MutableLiveData<List<Product>>().apply {
        value = listOf(
            Product(1, "Shirt", "Clothing", "Nike", "M", 25.0, 10, "A shirt", "Blue"),
            Product(2, "Pants", "Clothing", "Adidas", "L", 35.0, 5, "A pair of pants", "Black"),
            Product(3, "Shoes", "Footwear", "Nike", "10", 50.0, 3, "A pair of shoes", "White"),
            Product(4, "Hat", "Accessories", "Adidas", "One Size", 15.0, 20, "A hat", "Red"),
            Product(5, "Socks", "Clothing", "Nike", "One Size", 5.0, 100, "A pair of socks", "White")
        )
    }

    val products: LiveData<List<Product>> = _products
}