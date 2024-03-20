package com.example.salesmanagement.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.salesmanagement.databinding.FragmentProductBinding
import com.example.salesmanagement.R

class ProductFragment : Fragment()
{
    private var _binding: FragmentProductBinding? = null

    private lateinit var productViewModel: ProductViewModel

    private val binging get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binging.root

        initRecyclerView()

        initListeners()

        return root
    }

    private fun initListeners()
    {
        binging.fabAddProduct.setOnClickListener { goToAddProduct() }
    }

    private fun initRecyclerView()
    {
        val recyclerView = binging.rvProduct
        val adapter = ProductAdapter(binging.tvEmpty)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(requireContext())

        productViewModel.products.observe(viewLifecycleOwner) { product ->
            adapter.setData(product)
        }
    }

    private fun goToAddProduct()
    {
        findNavController().navigate(R.id.action_nav_product_to_nav_insert_product)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        _binding = null
    }
}