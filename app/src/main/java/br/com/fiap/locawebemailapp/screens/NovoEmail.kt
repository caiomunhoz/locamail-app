package br.com.fiap.locawebemailapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.EmailTopBar
import br.com.fiap.locawebemailapp.database.repository.EmailRepository
import br.com.fiap.locawebemailapp.model.Email
import br.com.fiap.locawebemailapp.model.EmailDb
import br.com.fiap.locawebemailapp.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun NovoEmail(navController: NavController) {
    val emailRepository = EmailRepository(LocalContext.current)

    var assunto by remember { mutableStateOf(TextFieldValue()) }
    var mensagem by remember { mutableStateOf(TextFieldValue()) }
    var emailDestinatario by remember { mutableStateOf(TextFieldValue()) }

    fun salvarEmailDb(email: Email) {
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
        val call = RetrofitFactory().getEmailService().enviarEmail(emailDb)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) println("Email salvo no BD") else println("Erro ao salvar no BD")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t)
            }

        })
    }

    Scaffold(
        topBar = {
            EmailTopBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = emailDestinatario,
                    onValueChange = { emailDestinatario = it },
                    label = { Text(text = "Destinatário") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = assunto,
                    onValueChange = { assunto = it },
                    label = { Text(text = "Assunto") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = mensagem,
                    onValueChange = { mensagem = it },
                    label = { Text(text = "Mensagem") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val novoEmail = Email(
                            assunto = assunto.text,
                            mensagem = mensagem.text,
                            emailDestinatario = emailDestinatario.text,
                            emailRemetente = "usuario.atual@email.com",
                            remetente = "Usuário Atual",
                            dataEnvio = LocalDate.now()
                        )
                        salvarEmailDb(novoEmail)
                        emailRepository.salvar(novoEmail)
                        navController.navigate("principal")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Enviar")
                }
            }
        }
    )
}
