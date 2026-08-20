package com.rbj.khata.data

data class Customer(
    val id: String,
    val name: String,
    val mobile: String,
    val village: String,
    val totalDue: Double = 0.0,
    val interestDue: Double = 0.0
)