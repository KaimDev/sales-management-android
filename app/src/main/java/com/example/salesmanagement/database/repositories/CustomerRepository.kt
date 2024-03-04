package com.example.salesmanagement.database.repositories

import com.example.salesmanagement.database.daos.CustomerDao
import com.example.salesmanagement.database.entities.Customer

class CustomerRepository(private val customerDao: CustomerDao)
{
    val customers = customerDao.getAll()

    suspend fun insert(customer: Customer)
    {
        customerDao.insert(customer)
    }

    fun delete(customer: Customer)
    {
        customerDao.delete(customer)
    }
}