package com.example.salesmanagement.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesmanagement.databinding.FragmentCustomerBinding
import com.example.salesmanagement.models.Customer

class CustomerFragment : Fragment()
{
    val Customers = listOf(
        Customer(1, "John Doe", "1234567890", "john@email.com", "123 Main St"),
        Customer(2, "Jane Doe", "0987654321", "jane@email.com","456 Main St"),
        Customer(3, "John Smith", "1234567890", "", "123 Main St"),
        Customer(4, "Lourdes Lopez", "1234567890", "lopez@email.com", ""),
        Customer(5, "John Doe", "1234567890", null, null)
    )

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

        binding.fabAddCustomer.setOnClickListener {
            customerViewModel.GoToAddCustomer()
        }

        initRecyclerView()

        return root
    }

    private fun initRecyclerView()
    {
        val rcCustomer = binding.rvCustomer
        rcCustomer.layoutManager = LinearLayoutManager(this.context)
        rcCustomer.adapter = CustomerAdapter(Customers)
    }
}