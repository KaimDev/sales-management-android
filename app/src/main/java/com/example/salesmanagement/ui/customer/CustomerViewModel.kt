package com.example.salesmanagement.ui.customer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.salesmanagement.database.DatabaseSingleton
import com.example.salesmanagement.database.entities.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CustomerViewModel: ViewModel()
{
    private fun getDatabase(context: Context) = DatabaseSingleton.getInstance(context)

    fun getCustomers(context: Context): MutableLiveData<List<Customer>>
    {
        val customerList = MutableLiveData<List<Customer>>()
        val db = getDatabase(context)

        var deferred = GlobalScope.async {
            customerList.value = db.customerDao().getAll()
        }

        GlobalScope.launch(Dispatchers.Main) {
            deferred.await()
        }

        return customerList
    }
}

