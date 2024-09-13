package br.com.fiap.locawebemailapp.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.database.repository.EmailRepository
import br.com.fiap.locawebemailapp.model.Email
import br.com.fiap.locawebemailapp.model.EmailDb
import br.com.fiap.locawebemailapp.service.RetrofitFactory
import br.com.fiap.locawebemailapp.ui.theme.Black
import br.com.fiap.locawebemailapp.ui.theme.Grey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.DateTimeFormatter
import kotlin.math.log

@Composable
fun EmailCard(
    navController: NavController,
    email: Email,
    isDarkTheme: Boolean,
    onDeleteEmail: () -> Unit
) {
    val emailRepository = EmailRepository(LocalContext.current)

    var favoritado by remember {
        mutableStateOf(email.favorito)
    }
    var openDialogConfirmarDelete by remember {
        mutableStateOf(false)
    }

    val nomeDisplay: String
    val emailDisplay: String

    if (email.emailRemetente != "usuario.atual@email.com") {
        nomeDisplay = email.remetente
        emailDisplay = email.emailRemetente
    } else {
        nomeDisplay = "Para: ${email.emailDestinatario}"
        emailDisplay = "Enviado por você"
    }

    fun deletarEmailDb(id: Long) {
        RetrofitFactory().getEmailService().deletarEmail(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) println("Email deletado no DB") else println("Erro ao deletar email no DB")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t)
            }
        })
    }

    fun atualizarFavoritoDb(email: Email) {
        val data = "${email.dataEnvio} + T00:00:00.000Z"
        val emailDb = EmailDb(
            assunto = email.assunto,
            mensagem = email.mensagem,
            emailRemetente = email.emailRemetente,
            emailDestinatario = email.emailDestinatario,
            remetente = email.remetente,
            dataEnvio = data,
            favorito = email.favorito
        )
        RetrofitFactory().getEmailService().atualizarFavorito(emailDb)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) println("Favorito atualizado") else println("Erro ao atualizar favorito")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t)
                }
            })

    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = {
                navController.navigate("email/${email.id}")
            })
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
                    text = email.dataEnvio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )
            }
            Text(
                text = emailDisplay
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
                            email.favorito = !email.favorito
                            atualizarFavoritoDb(email)
                            emailRepository.modificarFavorito(email)
                        }, modifier = Modifier.border(
                            width = 2.dp,
                            color = Color.Transparent,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "Favoritar",
                            tint = if (!favoritado) {
                                Color.Gray
                            } else if (isDarkTheme) {
                                Color.White
                            } else {
                                Color.Yellow
                            }
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
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(200.dp),
            confirmButton = {
                Button(
                    onClick = {
                        deletarEmailDb(email.id)
                        emailRepository.deletar(email)
                        openDialogConfirmarDelete = false
                        onDeleteEmail()
                    }
                ) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialogConfirmarDelete = false },
                ) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Deletar email") },
            text = { Text(text = "Deseja realmente deletar o email?") },
            shape = RoundedCornerShape(15.dp),
        )
    }
}