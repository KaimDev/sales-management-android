package com.example.salesmanagement.ui.customer.insert

import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InsertCustomerViewModel : ViewModel()
{
    fun validateCustomerName(textInputLayout: TextInputLayout): Boolean
    {
        val textInput = textInputLayout.editText as TextInputEditText
        if (textInput.text.toString().trim().isEmpty())
        {
            textInput.error = "Required Field!"
            textInput.requestFocus()
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
            textInput.error = "Required Field!"
            textInput.requestFocus()
            return false
        } else if (!regex.matches(textInput.text.toString()))
        {
            textInput.error = "Invalid Phone Number!"
            textInput.requestFocus()
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
        if (textInput.text.toString().trim().isNotEmpty() && !regex.matches(textInput.text.toString()))
        {
            textInput.error = "Invalid Email!"
            textInput.requestFocus()
            return false
        } else
        {
            textInputLayout.isErrorEnabled = false
        }

        return true
    }
}