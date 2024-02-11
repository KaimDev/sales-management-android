package com.example.salesmanagement.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesmanagement.databinding.FragmentCustomerBinding
import com.example.salesmanagement.models.Customer

class CustomerFragment : Fragment()
{
    private var _binding: FragmentCustomerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val customerViewModel =
            ViewModelProvider(this).get(CustomerViewModel::class.java)

        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView(customerViewModel.customers.value!!)

        return root
    }

    private fun initRecyclerView(customers: List<Customer>)
    {
        val rcCustomer = binding.rvCustomer
        rcCustomer.layoutManager = LinearLayoutManager(this.context)
        rcCustomer.adapter = CustomerAdapter(customers)
    }
}