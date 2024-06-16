package br.com.fiap.locawebemailapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.model.Email
import br.com.fiap.locawebemailapp.ui.theme.Black
import br.com.fiap.locawebemailapp.ui.theme.Grey
import java.time.format.DateTimeFormatter

@Composable
fun EmailCard(navController: NavController, email: Email) {
    Card (
        modifier = Modifier
            .padding(4.dp).clickable(onClick = {
                navController.navigate("email/${email.id}")
            }),
        border = BorderStroke(2.dp, Grey),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Black,
        )
    ){
        Column (
            modifier = Modifier.padding(8.dp),
        ){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = email.remetente,
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
                text = email.emailRemetente,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = email.assunto,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = email.mensagem.replace("\n", " "),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}
