import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun BarraFuncionalidades() {
    var showModal by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            leadingIcon = {
                IconButton(onClick = { showModal = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Teste",
                    )
                }
            },
            value = "",
            onValueChange = {},
            label = { Text(text = "Pesquisar no email")},
            modifier = Modifier.weight(1f),
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Pesquisar",
                    )
                }
            }
        )


    }

    if (showModal) {
        AlertDialog(
            containerColor = Color.Black,
            modifier = Modifier.border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.extraLarge),
            onDismissRequest = { showModal = false },
            title = { Text(
                text ="Configurações de Data",
                color = Color.White) },
            text = {
                Column {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Data Inicial") }
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Data Final") }
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { /*TODO*/ }
                        )
                        Text(
                            color = Color.White,
                            text = "Favoritos",
                            modifier = Modifier
                                .alignByBaseline()
                                .padding(top = 13.dp) // Ajuste o valor de acordo com o espaçamento desejado
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showModal = false },
                    colors = ButtonDefaults.buttonColors( // Configuração das cores do botão
                        containerColor = Color.Black, // Cor de fundo do botão preto
                        contentColor = Color.Gray // Cor do texto do botão (cinza)
                    ),
                    modifier = Modifier.border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.medium), // Borda cinza no botão
                    content = { Text("OK") }
                )
            }
        )
    }
}