package br.com.fiap.locawebemailapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.BarraFuncionalidades
import br.com.fiap.locawebemailapp.components.BarraNavegacao
import br.com.fiap.locawebemailapp.components.EmailCard
import br.com.fiap.locawebemailapp.model.Email
import kotlin.math.log

@Composable
fun Principal(navController: NavController, emails: List<Email>) {
    Scaffold(
        bottomBar = { BarraNavegacao(navController) },
        containerColor = Color.Black
    ) { innerPadding ->
        Box {
            Column(modifier = Modifier.padding(innerPadding)) {
                BarraFuncionalidades()
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    items(emails) {
                        Column {
                            EmailCard(navController, it)
                        }
                    }
                }
            }
        }
    }
}