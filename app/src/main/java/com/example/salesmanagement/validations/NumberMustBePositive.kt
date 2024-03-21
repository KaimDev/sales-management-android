package com.example.salesmanagement.validations

import com.google.android.material.textfield.TextInputLayout

class NumberMustBePositive(private val input: TextInputLayout) : ValidatorBase(input)
{
    override fun validate(): Boolean
    {
        // Must be a number and be positive
        val text = input.editText!!.text.toString().trim()
        return text.toDoubleOrNull() != null && text.toDouble() > 0
    }

    override fun getErrorMessage(): String
    {
        if (isEmpty())
            return "Required Field!"

        return "Number Must Be Positive!"
    }

    private fun isEmpty() : Boolean
    {
        return input.editText!!.text.toString().trim().isEmpty()
    }
}