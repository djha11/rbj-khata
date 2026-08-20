package com.rbj.khata.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(

    @PrimaryKey
    val id: String,

    val name: String,

    val mobile: String,

    val village: String,

    val totalDue: Double = 0.0,

    val interestDue: Double = 0.0
)