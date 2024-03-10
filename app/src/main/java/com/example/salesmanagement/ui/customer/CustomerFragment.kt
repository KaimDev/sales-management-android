package com.example.salesmanagement.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.salesmanagement.databinding.FragmentCustomerBinding
import com.example.salesmanagement.R

class CustomerFragment : Fragment()
{
    private var _binding: FragmentCustomerBinding? = null

    private lateinit var customerViewModel: CustomerViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        customerViewModel = ViewModelProvider(this)[CustomerViewModel::class.java]

        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()

        initListeners()

        return root
    }

    private fun initListeners()
    {
        binding.fabAddCustomer.setOnClickListener { goToAddCustomer() }
    }

    private fun initRecyclerView()
    {
        val recyclerView = binding.rvCustomer
        val adapter = CustomerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(requireContext())

        customerViewModel.customers.observe(viewLifecycleOwner) { customer ->
            adapter.setData(customer)
        }
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