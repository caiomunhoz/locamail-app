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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.EmailTopBar
import br.com.fiap.locawebemailapp.model.Email
import br.com.fiap.locawebemailapp.repository.EmailRepository
import java.time.LocalDate

@Composable
fun NovoEmail(navController: NavController) {
    var assunto by remember { mutableStateOf(TextFieldValue()) }
    var mensagem by remember { mutableStateOf(TextFieldValue()) }
    var emailDestinatario by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        containerColor = Color.Black,
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
                            id = "",
                            assunto = assunto.text,
                            mensagem = mensagem.text,
                            emailDestinatario = emailDestinatario.text,
                            emailRemetente = "usuario.atual@email.com.br",
                            remetente = "Usuário Atual",
                            dataEnvio = LocalDate.now()
                        )
                        EmailRepository.criarEmail(novoEmail)
                        navController.navigate("principal")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            2.dp,
                            Color.White,
                            RoundedCornerShape(8.dp)
                        ) // Add rounded white border
                        .background(
                            Color.Black,
                            RoundedCornerShape(8.dp)
                        ), // Set background color to black with rounded corners
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Enviar")
                }
            }
        }
    )
}