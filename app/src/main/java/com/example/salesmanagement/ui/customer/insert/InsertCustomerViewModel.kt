package com.example.salesmanagement.ui.customer.insert

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.salesmanagement.database.AppDatabase
import com.example.salesmanagement.database.entities.Customer
import com.example.salesmanagement.database.repositories.CustomerRepository
import com.google.android.material.textfield.TextInputEditText
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

    fun validateCustomerName(textInputLayout: TextInputLayout): Boolean
    {
        val textInput = textInputLayout.editText as TextInputEditText
        if (textInput.text.toString().trim().isEmpty())
        {
            textInputLayout.error = "Required Field!"
            textInputLayout.requestFocus()
            return false
        } else
        {
            textInputLayout.isErrorEnabled = false
        }

        return true
    }

    fun validateCustomerPhone(textInputLayout: TextInputLayout): Boolean
    {
        val textInput = textInputLayout.editText as TextInputEditText

        // Only numbers
        val regex = Regex("^[0-9]*\$")
        if (textInput.text.toString().trim().isEmpty())
        {
            textInputLayout.error = "Required Field!"
            textInputLayout.requestFocus()
            return false
        } else if (!regex.matches(textInput.text.toString()))
        {
            textInputLayout.error = "Invalid Phone Number!"
            textInputLayout.requestFocus()
            return false
        } else
        {
            textInputLayout.isErrorEnabled = false
        }

        return true
    }

    fun validateCustomerEmail(textInputLayout: TextInputLayout): Boolean
    {
        // Can be empty
        val textInput = textInputLayout.editText as TextInputEditText

        // Email validation
        val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")
        if (textInput.text.toString().trim()
                .isNotEmpty() && !regex.matches(textInput.text.toString())
        )
        {
            textInputLayout.error = "Invalid Email!"
            textInputLayout.requestFocus()
            return false
        } else
        {
            textInputLayout.isErrorEnabled = false
        }

        return true
    }

    fun saveCustomer(
        textInputLayout: TextInputLayout,
        tilCustomerPhone: TextInputLayout,
        tilCustomerEmail: TextInputLayout,
        tilCustomerAddress: TextInputLayout
    ) : Boolean
    {
        if (validateCustomerName(textInputLayout) &&
            validateCustomerPhone(tilCustomerPhone) &&
            validateCustomerEmail(tilCustomerEmail)
        )
        {
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

        return false
    }
}