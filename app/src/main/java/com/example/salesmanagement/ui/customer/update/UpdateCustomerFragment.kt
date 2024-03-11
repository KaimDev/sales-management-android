package com.example.salesmanagement.ui.customer.update

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.salesmanagement.R

class UpdateCustomerFragment : Fragment()
{

    companion object
    {
        fun newInstance() = UpdateCustomerFragment()
    }

    private val viewModel: UpdateCustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        return inflater.inflate(R.layout.fragment_update_customer, container, false)
    }
}