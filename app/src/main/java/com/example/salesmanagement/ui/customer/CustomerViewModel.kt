package com.example.salesmanagement.ui.customer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.salesmanagement.database.AppDatabase
import com.example.salesmanagement.database.entities.Customer
import com.example.salesmanagement.database.repositories.CustomerRepository

class CustomerViewModel(application: Application): AndroidViewModel(application)
{
    private val customers: LiveData<List<Customer>>
    private val customerRepository: CustomerRepository

    init
    {
        val customerDao = AppDatabase.getDatabase(application).customerDao()
        customerRepository = CustomerRepository(customerDao)
        customers = customerRepository.customers
    }
}

