package com.rbj.khata.ui.customers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddCustomerScreen(
    modifier: Modifier = Modifier,
    onSave: (
        name: String,
        mobile: String,
        village: String
    ) -> Unit,
    onBack: () -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }

    var mobile by rememberSaveable {
        mutableStateOf("")
    }

    var village by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Add Customer",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text("Customer Name")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = {
                Text("Mobile Number")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = village,
            onValueChange = { village = it },
            label = {
                Text("Village")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                onSave(
                    name,
                    mobile,
                    village
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank() &&
                    mobile.isNotBlank() &&
                    village.isNotBlank()
        ) {
            Text("Save Customer")
        }

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}