package com.example.salesmanagement.ui.customer.insert

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.salesmanagement.databinding.FragmentInsertCustomerBinding

import com.example.salesmanagement.R

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

        tilCustomerName.editText!!.addTextChangedListener(TextFieldValidation(tilCustomerName))
        tilCustomerPhone.editText!!.addTextChangedListener(TextFieldValidation(tilCustomerPhone))
        tilCustomerEmail.editText!!.addTextChangedListener(TextFieldValidation(tilCustomerEmail))

        _binding!!.fabSave.setOnClickListener {
            viewModel.saveCustomer(
                tilCustomerName,
                tilCustomerPhone,
                tilCustomerEmail,
                tilCustomerAddress
            )

            // back to previous fragment
            findNavController().navigate(R.id.action_nav_insert_customer_to_nav_customer)
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher
    {
        override fun afterTextChanged(s: Editable?)
        {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
        {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
        {
            when (view.id)
            {
                _binding!!.tilCustomerName.id -> viewModel.validateCustomerName(
                    _binding!!.tilCustomerName,
                )

                _binding!!.tilCustomerPhone.id -> viewModel.validateCustomerPhone(
                    _binding!!.tilCustomerPhone,
                )

                _binding!!.tilCustomerEmail.id -> viewModel.validateCustomerEmail(
                    _binding!!.tilCustomerEmail,
                )
            }
        }

    }
}