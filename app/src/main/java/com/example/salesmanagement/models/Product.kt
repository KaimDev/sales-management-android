package com.example.salesmanagement.models

data class Product (
    val id: Int,
    val name: String,
    val type: String,
    val brand: String,
    val size: String,
    val price: Double,
    val quantity: Int,
    val description: String?,
    val color: String?
)