package com.example.salesmanagement.validations

import com.google.android.material.textfield.TextInputLayout

class TextIsNotEmptyValidator(private val input: TextInputLayout) : ValidatorBase(input)
{
    override fun validate(): Boolean
    {
        val text = input.editText!!.text.toString().trim()
        return text.isNotEmpty()
    }

    override fun getErrorMessage(): String
    {
        return "Required Field!"
    }
}