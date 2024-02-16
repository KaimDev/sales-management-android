package com.example.salesmanagement.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesmanagement.databinding.FragmentProductBinding
import com.example.salesmanagement.models.Product
import com.example.salesmanagement.R

class ProductFragment : Fragment()
{
    private var _binding: FragmentProductBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productViewModel =
            ViewModelProvider(this).get(ProductViewModel::class.java)

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView(productViewModel.products.value!!)

        initListeners()

        return root
    }

    private fun initListeners()
    {
        binding.fabAddProduct.setOnClickListener { goToAddProduct() }
    }

    private fun goToAddProduct()
    {
        findNavController().navigate(R.id.action_nav_product_to_nav_insert_product)
    }

    private fun initRecyclerView(products: List<Product>)
    {
        val rcProduct = binding.rvProduct
        rcProduct.layoutManager = LinearLayoutManager(this.context)
        rcProduct.adapter = ProductAdapter(products)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        _binding = null
    }
}