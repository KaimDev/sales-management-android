package com.example.salesmanagement.ui.customer

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanagement.R
import com.example.salesmanagement.database.entities.Customer

class CustomerHolder(val view: View) : RecyclerView.ViewHolder(view)
{
    fun render(customer: Customer)
    {
        view.findViewById<TextView>(R.id.tvName).text = customer.name
        view.findViewById<TextView>(R.id.tvPhone).text = customer.phone
        view.findViewById<TextView>(R.id.tvEmail).text = customer.email
        view.findViewById<TextView>(R.id.tvAddress).text = customer.address
    }
}