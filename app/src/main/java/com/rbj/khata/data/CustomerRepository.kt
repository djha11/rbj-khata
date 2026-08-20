package com.rbj.khata.data

import com.rbj.khata.data.local.CustomerDao
import com.rbj.khata.data.local.CustomerEntity
import kotlinx.coroutines.flow.Flow

class CustomerRepository(
    private val customerDao: CustomerDao
) {

    fun getAllCustomers(): Flow<List<CustomerEntity>> {
        return customerDao.getAllCustomers()
    }

    suspend fun insertCustomer(customer: CustomerEntity) {
        customerDao.insertCustomer(customer)
    }

    suspend fun deleteCustomer(customer: CustomerEntity) {
        customerDao.deleteCustomer(customer)
    }
}
