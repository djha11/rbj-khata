package com.rbj.khata.ui.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbj.khata.data.CustomerRepository
import com.rbj.khata.data.local.CustomerEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val repository: CustomerRepository
) : ViewModel() {

    val customers: StateFlow<List<CustomerEntity>> =
        repository.getAllCustomers()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun addCustomer(
        name: String,
        mobile: String,
        village: String
    ) {
        val customer = CustomerEntity(
            id = "C" + System.currentTimeMillis(),
            name = name,
            mobile = mobile,
            village = village
        )

        viewModelScope.launch {
            repository.insertCustomer(customer)
        }
    }

    fun deleteCustomer(customer: CustomerEntity) {
        viewModelScope.launch {
            repository.deleteCustomer(customer)
        }
    }
}
