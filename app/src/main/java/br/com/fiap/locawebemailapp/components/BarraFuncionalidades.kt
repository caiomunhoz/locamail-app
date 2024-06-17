import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
@Composable
fun BarraFuncionalidades(
    onOrderChange: (Boolean) -> Unit,
    onFavoriteFilterChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit
) {
    //val emailRepository = EmailRepository(LocalContext.current)

    var showModal by remember { mutableStateOf(false) }
    var ascendingOrder by remember { mutableStateOf(false) }
    var showFavoriteFilter by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
            ),
            leadingIcon = {
                IconButton(onClick = { showModal = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Teste",
                    )
                }
            },
            value = searchText,
            onValueChange = {
                searchText = it
                onSearch(it)
            },
            label = { Text(text = "Pesquisar no email") },
            modifier = Modifier.weight(1f),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Pesquisar",
                )
            }
        )
    }
    if (showModal) {
        AlertDialog(
            containerColor = Color.Black,
            modifier = Modifier.border(
                BorderStroke(1.dp, Color.Gray),
                MaterialTheme.shapes.extraLarge
            ),
            onDismissRequest = { showModal = false },
            title = {
                Text(
                    text = "Filtros",
                    color = Color.White
                )
            },
            text = {
                Column {
                    Button(
                        onClick = {
                            ascendingOrder = !ascendingOrder
                            onOrderChange(ascendingOrder)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.medium)
                            .fillMaxWidth(),
                        content = {
                            Text(if (ascendingOrder) "ASC ↑" else "DESC ↓")
                        }
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = showFavoriteFilter,
                            onCheckedChange = {
                                showFavoriteFilter = it
                                onFavoriteFilterChange(it)
                            }
                        )
                        Text(
                            color = Color.White,
                            text = "Favoritos",
                            modifier = Modifier
                                .alignByBaseline()
                                .padding(top = 13.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showModal = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.border(
                        BorderStroke(1.dp, Color.Gray),
                        MaterialTheme.shapes.medium
                    ),
                    content = { Text("OK") }
                )
            }
        )
    }
}