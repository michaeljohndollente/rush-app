import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "My Cart",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Chick Fil-A - SM Megamall",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Light
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(
                           Icons.Filled.ArrowBack, // Replace with your back icon
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            CartItem()
            Divider(color = Color.LightGray, thickness = 1.dp)
            CartItem()
        }
    }
}

@Composable
fun CartItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = Color(0xFFFFE0E0), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "1x",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Frankie's Classic Buffalo (Regular)",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = "₱ 649.00",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Size - One Dozen\nSpice level - Super\nDip - Aioli\nNotes: Separate sauce please",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                OutlinedButton (
                    onClick = { /* Handle edit */ },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color(255, 165, 0)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(255, 165, 0), // Text color
                        containerColor = Color.White // Button background color
                    ),
                    modifier = Modifier.weight(1f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = "Edit")
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = { /* Handle remove */ },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                    modifier = Modifier.weight(1f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = "Remove")
                }
            }
        }
    }
}

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(0.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = Color(0xFFFFE0E0), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "2",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { /* Handle checkout */ },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(Color(255, 165, 0)),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Checkout",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "₱1,298.00",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
    }
}
