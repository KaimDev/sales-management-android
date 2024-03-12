package com.example.salesmanagement.ui.customer.insert

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.salesmanagement.database.AppDatabase
import com.example.salesmanagement.database.entities.Customer
import com.example.salesmanagement.database.repositories.CustomerRepository
import com.example.salesmanagement.validations.EmailValidator
import com.example.salesmanagement.validations.PhoneValidator
import com.example.salesmanagement.validations.TextIsNotEmptyValidator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertCustomerViewModel(application: Application) : AndroidViewModel(application)
{
    private val customers: LiveData<List<Customer>>
    private val customerRepository: CustomerRepository

    init
    {
        val customerDao = AppDatabase.getDatabase(application).customerDao()
        customerRepository = CustomerRepository(customerDao)
        customers = customerRepository.customers
    }

    private fun insertCustomer(customer: Customer)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            customerRepository.insert(customer)
        }
    }

    fun saveCustomer(
        textInputLayout: TextInputLayout,
        tilCustomerPhone: TextInputLayout,
        tilCustomerEmail: TextInputLayout,
        tilCustomerAddress: TextInputLayout
    ): Boolean
    {
        if (!validateInputs(textInputLayout, tilCustomerPhone, tilCustomerEmail)) return false

        val customer = Customer(
            id = 0,
            name = textInputLayout.editText!!.text.toString(),
            phone = tilCustomerPhone.editText!!.text.toString(),
            email = tilCustomerEmail.editText!!.text.toString(),
            address = tilCustomerAddress.editText!!.text.toString()
        )

        insertCustomer(customer)

        Toast.makeText(
            getApplication(),
            "Customer saved successfully!",
            Toast.LENGTH_SHORT
        ).show()

        return true
    }

    private fun validateInputs(
        inputName: TextInputLayout,
        inputPhone: TextInputLayout,
        inputEmail: TextInputLayout
    ): Boolean
    {
        val validateCustomerName = TextIsNotEmptyValidator(inputName)
        val validateCustomerPhone = PhoneValidator(inputPhone)
        val validateCustomerEmail = EmailValidator(inputEmail)

        var result = true

        if (!validateCustomerEmail.validate())
        {
            validateCustomerEmail.setInputError()
            result = false
        }

        if (!validateCustomerPhone.validate())
        {
            validateCustomerPhone.setInputError()
            result = false
        }

        if (!validateCustomerName.validate())
        {
            validateCustomerName.setInputError()
            result = false
        }

        return result
    }
}