package com.example.salesmanagement.ui.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanagement.R
import com.example.salesmanagement.database.entities.Customer

class CustomerAdapter(private val customerList: List<Customer>) : RecyclerView.Adapter<CustomerHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder
    {
        val layoutInflate = LayoutInflater.from(parent.context)
        return CustomerHolder(layoutInflate.inflate(R.layout.customer_view, parent, false))
    }

    override fun onBindViewHolder(holder: CustomerHolder, position: Int)
    {
        holder.render(customerList[position])
    }

    override fun getItemCount(): Int = customerList.size
}