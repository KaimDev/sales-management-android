package com.example.salesmanagement.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.salesmanagement.database.entities.Customer

@Dao
interface CustomerDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    @Query("SELECT * FROM customer ORDER BY id ASC")
    fun getAll(): LiveData<List<Customer>>

    @Query("SELECT * FROM customer WHERE id = :id")
    fun getById(id: Int): Customer

    @Delete
    fun delete(customer: Customer)

    @Update
    suspend fun update(customer: Customer)
}