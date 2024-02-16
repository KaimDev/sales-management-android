package com.example.salesmanagement.ui.product.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.salesmanagement.databinding.FragmentInsertProductBinding

class InsertProductFragment : Fragment()
{
    private var _binding: FragmentInsertProductBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentInsertProductBinding.inflate(inflater, container, false)
        val root = _binding!!.root

        return root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}