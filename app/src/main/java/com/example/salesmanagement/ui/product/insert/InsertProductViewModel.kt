package com.example.salesmanagement.ui.product.insert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.salesmanagement.database.AppDatabase
import com.example.salesmanagement.database.repositories.ProductRepository

class InsertProductViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository: ProductRepository

    init
    {
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
    }
}