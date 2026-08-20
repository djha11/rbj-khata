package com.rbj.khata.ui.customers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rbj.khata.data.local.CustomerEntity

@Composable
fun CustomerListScreen(
    viewModel: CustomerViewModel,
    modifier: Modifier = Modifier
) {
    val customers by viewModel.customers.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Customers",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "${customers.size} customers",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 16.dp
            )
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = customers,
                key = { customer -> customer.id }
            ) { customer ->
                CustomerCard(customer = customer)
            }
        }
    }
}

@Composable
private fun CustomerCard(
    customer: CustomerEntity
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = customer.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = customer.mobile,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = customer.village,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "Due: ₹${customer.totalDue}",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Interest: ₹${customer.interestDue}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}