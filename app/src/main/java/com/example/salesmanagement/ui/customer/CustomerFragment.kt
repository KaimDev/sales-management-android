package com.example.salesmanagement.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesmanagement.databinding.FragmentCustomerBinding
import com.example.salesmanagement.R

class CustomerFragment : Fragment()
{
    private var _binding: FragmentCustomerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val customerViewModel =
            ViewModelProvider(this).get(CustomerViewModel::class.java)

        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView(customerViewModel)

        initListeners()

        return root
    }

    private fun initListeners()
    {
        binding.fabAddCustomer.setOnClickListener { goToAddCustomer() }
    }

    private fun initRecyclerView(viewModel: CustomerViewModel)
    {
        val customers = viewModel.getCustomers(requireContext()).value ?: listOf()
        val rcCustomer = binding.rvCustomer
        rcCustomer.layoutManager = LinearLayoutManager(this.context)
        rcCustomer.adapter = CustomerAdapter(customers)
    }

    private fun goToAddCustomer()
    {
        findNavController().navigate(R.id.action_nav_customer_to_nav_insert_customer)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        _binding = null
    }
}