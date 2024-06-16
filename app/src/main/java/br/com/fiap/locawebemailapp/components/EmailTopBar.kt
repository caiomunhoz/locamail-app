package br.com.fiap.locawebemailapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTopBar(navController: NavController) {
    Box {
        TopAppBar(
            title = { Text(text = "", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("principal") }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.Gray)
                }
            },
            colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Gray divider below TopAppBar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 1.dp)
                .align(alignment = Alignment.BottomStart),
            color = Color.Gray
        ) {
            // Optional: You can add padding or other content inside the Surface if needed
        }
    }
}