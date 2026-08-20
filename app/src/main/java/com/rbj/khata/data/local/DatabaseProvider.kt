package com.rbj.khata.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: KhataDatabase? = null

    fun getDatabase(context: Context): KhataDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                KhataDatabase::class.java,
                "rbj_khata_database"
            ).build().also {
                INSTANCE = it
            }
        }
    }
}
