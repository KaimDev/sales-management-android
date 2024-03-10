package com.example.salesmanagement.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanagement.R
import com.example.salesmanagement.database.entities.Customer

class CustomerAdapter(private val tvEmpty: TextView) : RecyclerView.Adapter<CustomerAdapter.CustomerHolder>()
{
    private var customerList = emptyList<Customer>()

    class CustomerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder
    {
        return CustomerHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_view, parent, false))
    }

    override fun onBindViewHolder(holder: CustomerHolder, position: Int)
    {
        val currentItem = customerList[position]
        holder.itemView.findViewById<TextView>(R.id.tvName).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.tvPhone).text = currentItem.phone
        holder.itemView.findViewById<TextView>(R.id.tvEmail).text = currentItem.email
        holder.itemView.findViewById<TextView>(R.id.tvAddress).text = currentItem.address
    }

    override fun getItemCount(): Int = customerList.size

    fun setData(customers: List<Customer>)
    {
        customerList = customers
        notifyDataSetChanged()

        handleEmptyView()
    }

    private fun handleEmptyView()
    {
        if (customerList.isEmpty())
        {
            tvEmpty.visibility = View.VISIBLE
        }
        else
        {
            tvEmpty.visibility = View.GONE
        }
    }
}