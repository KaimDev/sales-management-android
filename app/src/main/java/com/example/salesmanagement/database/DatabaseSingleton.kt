package com.example.salesmanagement.database

import android.content.Context
import androidx.room.Room

class DatabaseSingleton private constructor()
{
    companion object
    {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase
        {
            return instance ?: synchronized(this)
            {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "sales-database"
                ).build()
            }
        }
    }
}