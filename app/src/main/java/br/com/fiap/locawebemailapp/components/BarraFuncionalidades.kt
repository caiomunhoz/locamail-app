import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Switch
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun BarraFuncionalidades(
    onOrderChange: (Boolean) -> Unit,
    onFavoriteFilterChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
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
            leadingIcon = {
                IconButton(onClick = { showModal = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
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
            modifier = Modifier.border(
                BorderStroke(1.dp, Color.Gray),
                MaterialTheme.shapes.extraLarge
            ),
            onDismissRequest = { showModal = false },
            title = {
                Text(
                    text = "Configurações"
                )
            },
            text = {
                Column {
                    Button(
                        onClick = {
                            ascendingOrder = !ascendingOrder
                            onOrderChange(ascendingOrder)
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
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
                            text = "Favoritos",
                            modifier = Modifier
                                .alignByBaseline()
                                .padding(top = 13.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { onThemeChange(it) }
                        )
                        Text("Tema Escuro")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showModal = false },
//                    modifier = Modifier.border(
//                        MaterialTheme.shapes.medium
//                    ),
                    shape = RoundedCornerShape(16.dp),
                    content = { Text("OK") }
                )
            }
        )
    }
}