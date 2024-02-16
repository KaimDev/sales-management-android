package com.example.salesmanagement.ui.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesmanagement.database.DatabaseSingleton
import com.example.salesmanagement.database.entities.Customer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerViewModel: ViewModel()
{
    private fun getDatabase(context: Context) = DatabaseSingleton.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun getCustomers(context: Context): MutableLiveData<List<Customer>>
    {
        val customerList = MutableLiveData<List<Customer>>()
        val db = getDatabase(context)

        GlobalScope.launch(Dispatchers.IO) {
            val customers = db.customerDao().getAll()
            withContext(Dispatchers.Main) {
                customerList.value = customers
            }
        }

        return customerList
    }
}

