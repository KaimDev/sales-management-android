package com.example.salesmanagement.ui.product.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.salesmanagement.databinding.FragmentUpdateProductBinding
import com.example.salesmanagement.validations.NumberMustBePositive
import com.example.salesmanagement.validations.TextIsNotEmptyValidator
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.example.salesmanagement.R
class UpdateProductFragment : Fragment(), MenuProvider
{
    private var _binding: FragmentUpdateProductBinding? = null
    private val args by navArgs<UpdateProductFragmentArgs>()
    private val viewModel: UpdateProductViewModel by viewModels()

    // Components
    private lateinit var tilProductName: TextInputLayout
    private lateinit var tilProductType: TextInputLayout
    private lateinit var tilProductBrand: TextInputLayout
    private lateinit var tilProductSize: TextInputLayout
    private lateinit var tilProductPrice: TextInputLayout
    private lateinit var tilProductQuantity: TextInputLayout
    private lateinit var tilProductDescription: TextInputLayout
    private lateinit var tilProductColor: TextInputLayout

    private lateinit var fabUpdateButton: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentUpdateProductBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root

        initializeComponents()
        configureComponents()
        initializeListeners()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        activity?.addMenuProvider(this, viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater)
    {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean
    {
        return handleItems(menuItem)
    }

    private fun handleItems(menuItem: MenuItem): Boolean
    {
        if (menuItem.itemId == R.id.action_delete)
        {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                viewModel.deleteProduct(args.currentProduct)
                findNavController().navigate(UpdateProductFragmentDirections.actionNavUpdateProductToNavProduct())
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.currentProduct.name}?")
            builder.setMessage("Are you sure you want to delete ${args.currentProduct.name}?")
            builder.create().show()

            return true
        }

        val navController = findNavController()
        return navController.navigateUp()
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

        fabUpdateButton = _binding!!.fabUpdateProduct
    }

    private fun configureComponents()
    {
        tilProductName.editText?.setText(args.currentProduct.name)
        tilProductType.editText?.setText(args.currentProduct.type)
        tilProductBrand.editText?.setText(args.currentProduct.brand)
        tilProductSize.editText?.setText(args.currentProduct.size)
        tilProductPrice.editText?.setText(args.currentProduct.price.toString())
        tilProductQuantity.editText?.setText(args.currentProduct.quantity.toString())
        tilProductDescription.editText?.setText(args.currentProduct.description)
        tilProductColor.editText?.setText(args.currentProduct.color)
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

        fabUpdateButton.setOnClickListener { updateProduct() }
    }

    private fun updateProduct()
    {
        val result = viewModel.updateProduct(
            args.currentProduct.id,
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
            findNavController().navigate(UpdateProductFragmentDirections.actionNavUpdateProductToNavProduct())
    }
}