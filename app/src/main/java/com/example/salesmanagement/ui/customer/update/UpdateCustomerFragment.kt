package com.example.salesmanagement.ui.customer.update

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.salesmanagement.databinding.FragmentUpdateCustomerBinding
import com.example.salesmanagement.validations.EmailValidator
import com.example.salesmanagement.validations.PhoneValidator
import com.example.salesmanagement.validations.TextIsNotEmptyValidator
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.example.salesmanagement.R

class UpdateCustomerFragment : Fragment(), MenuProvider
{
    private val viewModel: UpdateCustomerViewModel by viewModels()
    private var _binding: FragmentUpdateCustomerBinding? = null
    private val args by navArgs<UpdateCustomerFragmentArgs>()

    // Components
    private lateinit var tilCustomerName: TextInputLayout
    private lateinit var tilCustomerPhone: TextInputLayout
    private lateinit var tilCustomerEmail: TextInputLayout
    private lateinit var tilCustomerAddress: TextInputLayout
    private lateinit var fabUpdateButton: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentUpdateCustomerBinding.inflate(inflater, container, false)

        initializeComponents()
        configureComponents()
        setupListeners()

        return _binding!!.root
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
                viewModel.deleteCustomer(args.currentCustomer)
                findNavController().navigate(UpdateCustomerFragmentDirections.actionNavUpdateCustomerToNavCustomer())
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${args.currentCustomer.name}?")
            builder.setMessage("Are you sure you want to delete ${args.currentCustomer.name}?")
            builder.create().show()

            return true
        }

        val navController = findNavController()
        return navController.navigateUp()
    }

    private fun initializeComponents()
    {
        tilCustomerName = _binding!!.tilCustomerName
        tilCustomerPhone = _binding!!.tilCustomerPhone
        tilCustomerEmail = _binding!!.tilCustomerEmail
        tilCustomerAddress = _binding!!.tilCustomerAddress
        fabUpdateButton = _binding!!.fabUpdateCustomer
    }

    private fun configureComponents()
    {
        tilCustomerName.editText!!.setText(args.currentCustomer.name)
        tilCustomerPhone.editText!!.setText(args.currentCustomer.phone)
        tilCustomerEmail.editText!!.setText(args.currentCustomer.email)
        tilCustomerAddress.editText!!.setText(args.currentCustomer.address)
    }

    private fun setupListeners()
    {
        tilCustomerName.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilCustomerName))
        tilCustomerPhone.editText!!.addTextChangedListener(PhoneValidator(tilCustomerPhone))
        tilCustomerEmail.editText!!.addTextChangedListener(EmailValidator(tilCustomerEmail))

        fabUpdateButton.setOnClickListener {
            updateCustomer()
        }
    }

    private fun updateCustomer()
    {
        val result = viewModel.updateCustomer(
            args.currentCustomer.id,
            tilCustomerName,
            tilCustomerPhone,
            tilCustomerEmail,
            tilCustomerAddress
        )

        if (result)
        {
            // back to previous fragment
            findNavController().navigate(UpdateCustomerFragmentDirections.actionNavUpdateCustomerToNavCustomer())
        }
    }
}