package com.example.salesmanagement.ui.product.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.salesmanagement.databinding.FragmentInsertProductBinding
import com.example.salesmanagement.validations.NumberMustBePositive
import com.example.salesmanagement.validations.TextIsNotEmptyValidator
import com.google.android.material.textfield.TextInputLayout
import com.example.salesmanagement.R

class InsertProductFragment : Fragment()
{
    private var _binding: FragmentInsertProductBinding? = null
    private lateinit var viewModel: InsertProductViewModel

    // Components
    private lateinit var tilProductName: TextInputLayout
    private lateinit var tilProductType: TextInputLayout
    private lateinit var tilProductBrand: TextInputLayout
    private lateinit var tilProductSize: TextInputLayout
    private lateinit var tilProductPrice: TextInputLayout
    private lateinit var tilProductQuantity: TextInputLayout
    private lateinit var tilProductDescription: TextInputLayout
    private lateinit var tilProductColor: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        viewModel = InsertProductViewModel(requireActivity().application)

        _binding = FragmentInsertProductBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root

        initializeComponents()
        initializeListeners()

        return root
    }

    private fun initializeComponents()
    {
        tilProductName = _binding!!.tilProductName
        tilProductType = _binding!!.tilProductType
        tilProductBrand = _binding!!.tilProductBrand
        tilProductSize = _binding!!.tilProductSize
        tilProductPrice = _binding!!.tilProductPrice
        tilProductQuantity = _binding!!.tilProductQuantity
        tilProductDescription = _binding!!.tilProductDescription
        tilProductColor = _binding!!.tilProductColor
    }

    private fun initializeListeners()
    {
        // Add Validators
        tilProductName.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilProductName))
        tilProductType.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilProductType))
        tilProductBrand.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilProductBrand))
        tilProductSize.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilProductSize))
        tilProductPrice.editText!!.addTextChangedListener(NumberMustBePositive(tilProductPrice))
        tilProductQuantity.editText!!.addTextChangedListener(NumberMustBePositive(tilProductQuantity))

        _binding!!.fabSave.setOnClickListener {
            val result = viewModel.saveProduct(
                tilProductName,
                tilProductType,
                tilProductBrand,
                tilProductSize,
                tilProductPrice,
                tilProductQuantity,
                tilProductDescription,
                tilProductColor
            )

            if (result)
            {
                // back to previous fragment
                findNavController().navigate(R.id.action_nav_insert_product_to_nav_product)
            }
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}