package com.example.salesmanagement.ui.product.insert

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesmanagement.database.AppDatabase
import com.example.salesmanagement.database.entities.Product
import com.example.salesmanagement.database.repositories.ProductRepository
import com.example.salesmanagement.validations.NumberMustBePositive
import com.example.salesmanagement.validations.TextIsNotEmptyValidator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertProductViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository: ProductRepository

    init
    {
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
    }

    fun saveProduct(
        tilProductName: TextInputLayout,
        tilProductType: TextInputLayout,
        tilProductBrand: TextInputLayout,
        tilProductSize: TextInputLayout,
        tilProductPrice: TextInputLayout,
        tilProductQuantity: TextInputLayout,
        tilProductDescription: TextInputLayout,
        tilProductColor: TextInputLayout
    ) : Boolean
    {
        if (!validateInputs(
                tilProductName,
                tilProductType,
                tilProductBrand,
                tilProductSize,
                tilProductPrice,
                tilProductQuantity
            )
        ) return false

        val product = Product(
            id = 0,
            name = tilProductName.editText!!.text.toString(),
            type = tilProductType.editText!!.text.toString(),
            brand = tilProductBrand.editText!!.text.toString(),
            size = tilProductSize.editText!!.text.toString(),
            price = tilProductPrice.editText!!.text.toString().toDouble(),
            quantity = tilProductQuantity.editText!!.text.toString().toInt(),
            description = tilProductDescription.editText!!.text.toString(),
            color = tilProductColor.editText!!.text.toString()
        )

        insertProduct(product)

        Toast.makeText(
            getApplication(),
            "Product saved successfully!",
            Toast.LENGTH_SHORT
        ).show()

        return true
    }

    private fun insertProduct(product: Product)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insert(product)
        }
    }

    private fun validateInputs(
        tilProductName: TextInputLayout,
        tilProductType: TextInputLayout,
        tilProductBrand: TextInputLayout,
        tilProductSize: TextInputLayout,
        tilProductPrice: TextInputLayout,
        tilProductQuantity: TextInputLayout,
    ): Boolean
    {
        val validateProductName = TextIsNotEmptyValidator(tilProductName)
        val validateProductType = TextIsNotEmptyValidator(tilProductType)
        val validateProductBrand = TextIsNotEmptyValidator(tilProductBrand)
        val validateProductSize = TextIsNotEmptyValidator(tilProductSize)
        val validateProductPrice = NumberMustBePositive(tilProductPrice)
        val validateProductQuantity = NumberMustBePositive(tilProductQuantity)

        var result = true

        if (!validateProductName.validate())
        {
            validateProductName.setInputError()
            result = false
        }

        if (!validateProductType.validate())
        {
            validateProductType.setInputError()
            result = false
        }

        if (!validateProductBrand.validate())
        {
            validateProductBrand.setInputError()
            result = false
        }

        if (!validateProductSize.validate())
        {
            validateProductSize.setInputError()
            result = false
        }

        if (!validateProductPrice.validate())
        {
            validateProductPrice.setInputError()
            result = false
        }

        if (!validateProductQuantity.validate())
        {
            validateProductQuantity.setInputError()
            result = false
        }

        return result
    }
}