package com.example.salesmanagement.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.salesmanagement.database.entities.Product

@Dao
interface ProductDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("SELECT * FROM product ORDER BY id ASC")
    fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getById(id: Int): Product

    @Update
    suspend fun update(product: Product)

    @Delete
    fun delete(product: Product)
}