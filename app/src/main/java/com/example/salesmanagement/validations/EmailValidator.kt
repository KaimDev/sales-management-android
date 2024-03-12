package com.example.salesmanagement.validations

import com.google.android.material.textfield.TextInputLayout

class EmailValidator(private val input: TextInputLayout) : ValidatorBase(input)
{
    override fun validate(): Boolean
    {
        // Can be optional but it must be a valid email
        val text = input.editText!!.text.toString().trim()
        val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")
        return text.isEmpty() || regex.matches(text)
    }

    override fun getErrorMessage(): String
    {
        return "Invalid Email!"
    }
}