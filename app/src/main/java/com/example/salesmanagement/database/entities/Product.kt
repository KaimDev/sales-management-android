package com.example.salesmanagement.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,
    val type: String,
    val brand: String,
    val size: String,
    val price: Double,
    val quantity: Int,
    val description: String?,
    val color: String?
) : Parcelable