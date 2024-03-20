package com.example.salesmanagement.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.salesmanagement.database.AppDatabase
import com.example.salesmanagement.database.entities.Product
import com.example.salesmanagement.database.repositories.ProductRepository

class ProductViewModel(application: Application) : AndroidViewModel(application)
{
    val products: LiveData<List<Product>>
    private val productRepository: ProductRepository

    init
    {
        val productDao = AppDatabase.getDatabase(application).productDao()
        productRepository = ProductRepository(productDao)
        products = productRepository.products
    }
}