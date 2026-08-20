package com.rbj.khata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rbj.khata.ui.theme.RBJKhataTheme
import com.rbj.khata.ui.customers.CustomerListScreen
import androidx.compose.runtime.mutableStateOf
import com.rbj.khata.ui.customers.AddCustomerScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rbj.khata.data.CustomerRepository
import com.rbj.khata.data.local.DatabaseProvider
import com.rbj.khata.ui.customers.CustomerViewModel
import com.rbj.khata.ui.customers.CustomerViewModelFactory
private val RBJGreen = Color(0xFF1B5E20)
private val RBJGreenLight = Color(0xFFE8F5E9)
private val RBJGold = Color(0xFFC49A3A)
private val RBJGoldLight = Color(0xFFFFF8E1)
private val RBJRed = Color(0xFFC62828)
private val RBJRedLight = Color(0xFFFFEBEE)
private val RBJBackground = Color(0xFFFFFBF5)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RBJKhataTheme {
                RBJKhataApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RBJKhataApp() {

    var selectedTab by remember { mutableIntStateOf(0) }
    var showAddCustomer by remember { mutableStateOf(false) }

    val database = DatabaseProvider.getDatabase(
        context = androidx.compose.ui.platform.LocalContext.current
    )

    val repository = remember {
        CustomerRepository(database.customerDao())
    }

    val customerViewModel: CustomerViewModel = viewModel(
        factory = CustomerViewModelFactory(repository)
    )
    Scaffold(
        containerColor = RBJBackground,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "RBJ Khata",
                        fontWeight = FontWeight.Bold,
                        color = RBJGreen
                    )
                },
                actions = {
                    Text(
                        text = "🔔",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RBJBackground
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Add Customer will be connected later
                },
                containerColor = RBJGreen,
                contentColor = Color.White
            ) {
                Text(
                    text = "+",
                    fontSize = 28.sp
                )
            }
        },

        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = {
                        showAddCustomer = true
                    },                    icon = {
                        Text("⌂", fontSize = 22.sp)
                    },
                    label = {
                        Text("Dashboard")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Text("♙", fontSize = 22.sp)
                    },
                    label = {
                        Text("Customers")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Text("🔔", fontSize = 18.sp)
                    },
                    label = {
                        Text("Alerts")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = {
                        Text("☰", fontSize = 20.sp)
                    },
                    label = {
                        Text("More")
                    }
                )
            }
        }
    ) { innerPadding ->

        when (selectedTab) {
            0 -> DashboardContent(
                modifier = Modifier.padding(innerPadding)
            )

            1 -> {
                if (showAddCustomer) {
                    AddCustomerScreen(
                        onSave = { name, mobile, village ->
                            customerViewModel.addCustomer(
                                name = name,
                                mobile = mobile,
                                village = village
                            )
                            showAddCustomer = false
                        },
                        onBack = {
                            showAddCustomer = false
                        }
                    )
                } else {
                    CustomerListScreen()
                }
            }
            2 -> {
                Text(
                    text = "Alerts",
                    modifier = Modifier.padding(innerPadding)
                )
            }

            3 -> {
                Text(
                    text = "More",
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun DashboardContent(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 90.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        // Active Workspace
        Text(
            text = "Active Workspace",
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = RBJGoldLight
                ) {
                    BoxContent()
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Rambihari Jha",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Grandfather's Khata",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "▼",
                    color = RBJGreen,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Summary cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SummaryCard(
                modifier = Modifier.weight(1f),
                title = "OVERDUE",
                value = "₹45,000",
                subtitle = "Interest",
                backgroundColor = RBJRedLight,
                valueColor = RBJRed
            )

            SummaryCard(
                modifier = Modifier.weight(1f),
                title = "DUE TODAY",
                value = "₹18,000",
                subtitle = "Interest",
                backgroundColor = RBJGoldLight,
                valueColor = RBJGold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            SummaryCard(
                modifier = Modifier.weight(1f),
                title = "ACTIVE LOANS",
                value = "42",
                subtitle = "",
                backgroundColor = RBJGreenLight,
                valueColor = RBJGreen
            )

            SummaryCard(
                modifier = Modifier.weight(1f),
                title = "CUSTOMERS",
                value = "36",
                subtitle = "",
                backgroundColor = Color.White,
                valueColor = RBJGreen
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Search
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "🔍",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Search customer by name, mobile or village",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Today's Interest
        SectionHeader(
            title = "Today's Interest",
            action = "View all →"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomerDueCard(
            name = "Ram Bihari Jha",
            location = "Rampur • 98XXXXXXXX",
            amount = "₹3,000",
            status = "Interest due today",
            statusColor = RBJGold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Overdue
        SectionHeader(
            title = "Overdue",
            action = "View all →"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomerDueCard(
            name = "Suresh Kumar",
            location = "Madhopur • 97XXXXXXXX",
            amount = "₹6,000",
            status = "2 days late",
            statusColor = RBJRed
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomerDueCard(
            name = "Raj Kumar",
            location = "Patna • 96XXXXXXXX",
            amount = "₹9,000",
            status = "7 days late",
            statusColor = RBJRed
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun BoxContent() {
    BoxCenter(
        text = "👴"
    )
}

@Composable
fun BoxCenter(
    text: String
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 22.sp
        )
    }
}

@Composable
fun SummaryCard(
    modifier: Modifier,
    title: String,
    value: String,
    subtitle: String,
    backgroundColor: Color,
    valueColor: Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = value,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = valueColor
            )

            if (subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    action: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = action,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = RBJGreen
        )
    }
}

@Composable
fun CustomerDueCard(
    name: String,
    location: String,
    amount: String,
    status: String,
    statusColor: Color
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = location,
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = amount,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = statusColor
                    )

                    Text(
                        text = status,
                        fontSize = 12.sp,
                        color = statusColor
                    )
                }

                Text(
                    text = "›",
                    fontSize = 28.sp,
                    color = Color.Gray
                )
            }
        }
    }
}