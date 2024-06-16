package br.com.fiap.locawebemailapp.components

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun BarraNavegacao(navController: NavController) {
    var itemSelecionado by remember { mutableIntStateOf(0) }
    val itens = listOf(
        "Caixa de Entrada",
        "Calendário",
        "Novo email"
    )
    val icones = listOf(
        Icons.Filled.Email,
        Icons.Filled.DateRange,
        Icons.Filled.Add
    )
    val context = LocalContext.current

    NavigationBar(
        containerColor = Color.Black
    ) {
        itens.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        icones[index],
                        contentDescription = item,
                        tint = Color.Gray
                    )
                },
                label = { Text(text = item, color = Color.Gray) },
                selected = itemSelecionado == index,
                onClick = {
                    itemSelecionado = index
                    when (itemSelecionado) {
                        0 -> {
                            if (navController.currentDestination?.route != "principal") {
                                navController.navigate("principal")
                            }
                        }

                        1 -> {
                            val intent = Intent(Intent.ACTION_MAIN).apply {
                                addCategory(Intent.CATEGORY_APP_CALENDAR)
                            }
                            context.startActivity(intent)
                        }

                        2 -> {
                            navController.navigate("novoEmail")
                        }
                    }
                },
            )
        }
    }
}
