package com.example.salesmanagement.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.salesmanagement.database.entities.Customer

@Dao
interface CustomerDao
{
    @Insert
    fun insert(customer: Customer)

    @Query("SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Query("SELECT * FROM customer WHERE id = :id")
    fun getById(id: Int): Customer

    @Delete
    fun delete(customer: Customer)
}