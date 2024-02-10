package com.example.salesmanagement.models

data class Customer (
    val id: Int,
    val name: String,
    val phone: String,
    val email: String?,
    val address: String?
)