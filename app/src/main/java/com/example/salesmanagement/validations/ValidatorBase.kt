package com.example.salesmanagement.validations

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

open class ValidatorBase(private val input: TextInputLayout) : TextWatcher
{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
    {
        return
    }

    override fun afterTextChanged(s: Editable?)
    {
        return
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
    {
        if (validate())
            setInputSuccess()
        else
            setInputError()
    }

    open fun setInputSuccess()
    {
        input.isErrorEnabled = false
    }

    open fun setInputError()
    {
        input.error = getErrorMessage()
        input.requestFocus()
    }

    open fun validate(): Boolean
    {
        return true
    }

    open fun getErrorMessage(): String
    {
        return "Invalid Input!"
    }
}