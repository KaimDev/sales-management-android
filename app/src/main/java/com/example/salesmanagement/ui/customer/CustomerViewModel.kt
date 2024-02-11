package com.example.salesmanagement.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesmanagement.models.Customer

class CustomerViewModel : ViewModel()
{
    // List of customers
    private val _customers = MutableLiveData<List<Customer>>().apply {
        value = listOf(
            Customer(1, "John Doe", "1234567890", "john@email.com", "123 Main St"),
            Customer(2, "Jane Doe", "0987654321", "jane@email.com", "456 Main St"),
            Customer(3, "John Smith", "1234567890", "", "123 Main St"),
            Customer(4, "Lourdes Lopez", "1234567890", "lopez@email.com", ""),
            Customer(5, "John Doe", "1234567890", null, null)
        )
    }

    val customers: LiveData<List<Customer>> = _customers
}