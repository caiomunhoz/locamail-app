package br.com.fiap.locawebemailapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.EmailTopBar
import br.com.fiap.locawebemailapp.database.repository.EmailRepository
import java.time.format.DateTimeFormatter

@Composable
fun Email(navController: NavController, emailId: String) {

    val emailRepository = EmailRepository(LocalContext.current)
    val email = emailRepository.buscarEmailPorId(emailId.toInt())

    Scaffold(
        topBar = { EmailTopBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            email.let {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.assunto,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Divider(thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "De: ${it.emailRemetente}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Text(
                        text = "Para: ${it.emailDestinatario}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Divider(thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Remetente: ${it.remetente}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Divider(thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.mensagem,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Enviado em: ${it.dataEnvio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}",
                        style = MaterialTheme.typography.labelSmall.copy(),
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.End)
                    )

                }
            }
        }
    }
}