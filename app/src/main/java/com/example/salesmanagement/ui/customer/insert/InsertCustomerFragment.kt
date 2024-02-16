package com.example.salesmanagement.ui.customer.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.salesmanagement.databinding.FragmentInsertCustomerBinding

class InsertCustomerFragment : Fragment()
{
    private var _binding: FragmentInsertCustomerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentInsertCustomerBinding.inflate(inflater, container, false);
        val root: View = _binding!!.root

        return root
    }
}