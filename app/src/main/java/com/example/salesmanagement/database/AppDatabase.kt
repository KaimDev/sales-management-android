package com.example.salesmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.salesmanagement.database.daos.CustomerDao
import com.example.salesmanagement.database.daos.ProductDao
import com.example.salesmanagement.database.entities.Customer
import com.example.salesmanagement.database.entities.Product
import kotlin.concurrent.Volatile

@Database(entities = [Customer::class, Product::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun customerDao(): CustomerDao
    abstract fun productDao(): ProductDao

    companion object
    {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase
        {
            val tempInstance = INSTANCE
            if (tempInstance != null)
            {
                return tempInstance
            }

            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sales-database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}