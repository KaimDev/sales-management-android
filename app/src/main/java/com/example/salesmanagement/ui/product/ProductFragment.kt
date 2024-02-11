package com.example.salesmanagement.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salesmanagement.databinding.FragmentProductBinding
import com.example.salesmanagement.models.Product

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

        return root
    }

    private fun initRecyclerView(products: List<Product>)
    {
        val rcProduct = binding.rvProduct
        rcProduct.layoutManager = LinearLayoutManager(this.context)
        rcProduct.adapter = ProductAdapter(products)
    }
}