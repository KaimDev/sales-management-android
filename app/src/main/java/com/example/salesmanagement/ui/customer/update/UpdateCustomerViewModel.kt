package com.example.salesmanagement.ui.customer.update

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
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
import kotlinx.coroutines.withContext

class UpdateCustomerViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository: CustomerRepository

    init
    {
        val customerDao = AppDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(customerDao)
    }

    fun updateCustomer(
        id: Int,
        nameLayout: TextInputLayout,
        phoneLayout: TextInputLayout,
        emailLayout: TextInputLayout,
        addressLayout: TextInputLayout
    ): Boolean
    {
        if (validateInputs(nameLayout, phoneLayout, emailLayout))
        {
            val customer = Customer(
                id,
                name = nameLayout.editText!!.text.toString(),
                phone = phoneLayout.editText!!.text.toString(),
                email = emailLayout.editText!!.text.toString(),
                address = addressLayout.editText!!.text.toString()
            )

            update(customer)

            return true
        }
        return false
    }

    private fun update(customer: Customer)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.update(customer)

            withContext(Dispatchers.Main)
            {
                Toast.makeText(getApplication(), "Customer updated", Toast.LENGTH_SHORT).show()
            }
        }
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

    fun deleteCustomer(customer: Customer)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.delete(customer)

            withContext(Dispatchers.Main)
            {
                Toast.makeText(getApplication(), "Customer deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}