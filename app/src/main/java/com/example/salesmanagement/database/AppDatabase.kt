package com.example.salesmanagement.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.salesmanagement.database.daos.CustomerDao
import com.example.salesmanagement.database.entities.Customer

@Database(entities = [Customer::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun customerDao(): CustomerDao
}