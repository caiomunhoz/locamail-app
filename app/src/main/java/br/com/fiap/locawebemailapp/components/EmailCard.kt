package br.com.fiap.locawebemailapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.model.Email
import br.com.fiap.locawebemailapp.repository.EmailRepository
import br.com.fiap.locawebemailapp.ui.theme.Black
import br.com.fiap.locawebemailapp.ui.theme.Grey
import java.time.format.DateTimeFormatter

@Composable
fun EmailCard(navController: NavController, email: Email) {
    var favoritado by remember {
        mutableStateOf(false)
    }
    var openDialogConfirmarDelete by remember {
        mutableStateOf(false)
    }


    val autor = email.emailRemetente != "usuario.atual@email.com"
    var nomeDisplay = ""
    var emailDisplay = ""

    if (autor) {
        nomeDisplay = email.remetente
        emailDisplay = email.emailRemetente
    } else {
        nomeDisplay = "Para: ${email.emailDestinatario}"
        emailDisplay = "Enviado por você"
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = {
                navController.navigate("email/${email.id}")
            }), border = BorderStroke(2.dp, Grey), colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Black,
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = nomeDisplay,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = email.dataEnvio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    color = Color.Gray
                )
            }
            Text(
                text = emailDisplay, color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = email.assunto,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = email.mensagem.replace("\n", " "),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Column {
                    IconButton(
                        onClick = {
                            favoritado = !favoritado
                            EmailRepository.modificarFavorito(email)
                        }, modifier = Modifier.border(
                            width = 2.dp,
                            color = Color.Transparent,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Favoritar",
                            tint = if (favoritado) Color.White else Color.Gray
                        )
                    }
                    IconButton(onClick = { openDialogConfirmarDelete = true }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Deletar",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
    if (openDialogConfirmarDelete) {
        AlertDialog(
            onDismissRequest = {
                openDialogConfirmarDelete = false
            },
            confirmButton = {
                Button(onClick = {
                    EmailRepository.deletarEmailPorId(email.id)
                    openDialogConfirmarDelete = false
                }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { openDialogConfirmarDelete = false }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Deletar email") },
            text = { Text(text = "Deseja realmente deletar o email?") },
            containerColor = Color.Black,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.height(200.dp)
        )
    }
}
