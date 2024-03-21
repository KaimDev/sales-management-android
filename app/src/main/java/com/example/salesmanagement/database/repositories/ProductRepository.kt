package com.example.salesmanagement.database.repositories

import com.example.salesmanagement.database.daos.ProductDao
import com.example.salesmanagement.database.entities.Product

class ProductRepository(private val productDao: ProductDao)
{
    val products = productDao.getAll()

    suspend fun insert(product: Product)
    {
        productDao.insert(product)
    }

    fun delete(product: Product)
    {
        productDao.delete(product)
    }

    fun update(product: Product)
    {
        productDao.update(product)
    }
}