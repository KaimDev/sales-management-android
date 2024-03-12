package com.example.salesmanagement.validations

import com.google.android.material.textfield.TextInputLayout

class PhoneValidator(private val input: TextInputLayout) : ValidatorBase(input)
{
    override fun validate(): Boolean
    {
        // Only numbers
        val regex = Regex("^[0-9]*\$")
        val text = input.editText!!.text.toString().trim()
        return regex.matches(text) && text.isNotEmpty()
    }

    override fun getErrorMessage(): String
    {
        return "Invalid Phone Number!"
    }
}