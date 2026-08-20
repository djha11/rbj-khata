package com.rbj.khata.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CustomerEntity::class],
    version = 1,
    exportSchema = true
)
abstract class KhataDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
}
