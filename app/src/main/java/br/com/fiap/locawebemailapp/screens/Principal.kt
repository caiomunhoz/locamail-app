package br.com.fiap.locawebemailapp.screens

import BarraFuncionalidades
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.BarraNavegacao
import br.com.fiap.locawebemailapp.components.EmailCard
import br.com.fiap.locawebemailapp.repository.EmailRepository

@Composable
fun Principal(
    navController: NavController
) {
    var ordemAscendente by remember { mutableStateOf(false) }
    var favoritos by remember { mutableStateOf(false) }
    var searchText by remember {mutableStateOf("")}
    var emails by remember { mutableStateOf(EmailRepository.listarEmailsDesc()) }

    fun atualizarEmails() {
        emails = when {
            favoritos -> EmailRepository.listarEmailsFavoritos()
            ordemAscendente -> EmailRepository.listarEmailsAsc()
            else -> EmailRepository.listarEmailsDesc()
        }
        if(searchText.isNotBlank()) {
            emails = EmailRepository.listarEmailsPorUser(emails, searchText)
        }
    }

    LaunchedEffect(ordemAscendente, favoritos) {
        atualizarEmails()
    }

    Scaffold(
        bottomBar = { BarraNavegacao(navController) },
        containerColor = Color.Black
    ) { innerPadding ->
        Box {
            Column(modifier = Modifier.padding(innerPadding)) {
                BarraFuncionalidades(
                    onOrderChange = { newOrder ->
                        ordemAscendente = newOrder
                        atualizarEmails()
                    },
                    onFavoriteFilterChange = { newShowFavorites ->
                        favoritos = newShowFavorites
                        atualizarEmails()
                    },
                    onSearch = { query ->
                        searchText = query
                        atualizarEmails()
                    }
                )
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
