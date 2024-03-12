package com.example.salesmanagement.ui.customer.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.salesmanagement.databinding.FragmentInsertCustomerBinding

import com.example.salesmanagement.R
import com.example.salesmanagement.validations.EmailValidator
import com.example.salesmanagement.validations.PhoneValidator
import com.example.salesmanagement.validations.TextIsNotEmptyValidator

class InsertCustomerFragment : Fragment()
{
    private var _binding: FragmentInsertCustomerBinding? = null
    private lateinit var viewModel: InsertCustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        viewModel = ViewModelProvider(this)[InsertCustomerViewModel::class.java]

        _binding = FragmentInsertCustomerBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root

        setUpListeners()

        return root
    }

    private fun setUpListeners()
    {
        val tilCustomerName = _binding!!.tilCustomerName
        val tilCustomerPhone = _binding!!.tilCustomerPhone
        val tilCustomerEmail = _binding!!.tilCustomerEmail
        val tilCustomerAddress = _binding!!.tilCustomerAddress

        tilCustomerName.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilCustomerName))
        tilCustomerPhone.editText!!.addTextChangedListener(PhoneValidator(tilCustomerPhone))
        tilCustomerEmail.editText!!.addTextChangedListener(EmailValidator(tilCustomerEmail))


        _binding!!.fabSave.setOnClickListener {
            val result = viewModel.saveCustomer(
                tilCustomerName,
                tilCustomerPhone,
                tilCustomerEmail,
                tilCustomerAddress
            )

            if (result)
            {
                // back to previous fragment
                findNavController().navigate(R.id.action_nav_insert_customer_to_nav_customer)
            }
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}