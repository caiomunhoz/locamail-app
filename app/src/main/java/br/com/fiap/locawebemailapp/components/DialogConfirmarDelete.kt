package br.com.fiap.locawebemailapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.repository.EmailRepository

@Composable
fun DialogConfirmarDelete(navController: NavController, emailId: String) {
    Dialog(onDismissRequest = { navController.popBackStack() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Deseja deletar esse email?")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = { navController.popBackStack()},
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Cancelar")
                    }
                    TextButton(
                        onClick = { EmailRepository.deletarEmailPorId(emailId)},
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Confirmar")
                    }
                }

            }

        }
    }
}