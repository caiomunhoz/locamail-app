package br.com.fiap.locawebemailapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DeleteButton(emailId: Int){
    IconButton(
        //colors = IconButtonDefaults.iconButtonColors(Color.Red),
        onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Deletar",
            tint = Color.Gray
        )
    }
}