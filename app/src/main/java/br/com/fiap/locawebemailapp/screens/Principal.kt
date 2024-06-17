package br.com.fiap.locawebemailapp.screens

import BarraFuncionalidades
import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.BarraNavegacao
import br.com.fiap.locawebemailapp.components.EmailCard
import br.com.fiap.locawebemailapp.database.repository.EmailRepository
import br.com.fiap.locawebemailapp.model.mock.emailsMockados

@Composable
fun Principal(
    navController: NavController
) {
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)

    LaunchedEffect(Unit) {
        if (isFirstRun(context)) {
            val emailsMockados = emailsMockados()
            emailRepository.salvarMultiplos(emailsMockados)
        }
    }

    var ordemAscendente by remember { mutableStateOf(false) }
    var favoritos by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var emails by remember { mutableStateOf(emailRepository.listarEmailsDesc()) }

    fun atualizarEmails() {
        emails = when {
            favoritos -> emailRepository.listarFavoritos()
            ordemAscendente -> emailRepository.listarEmailsAsc()
            else -> emailRepository.listarEmailsDesc()
        }
        if (searchText.isNotBlank()) {
            emails = emailRepository.listarPorBusca(searchText)
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
                    items(emails) { email ->
                        Column {
                            EmailCard(navController, email) {
                                atualizarEmails()
                            }
                        }
                    }
                }
            }
        }
    }
}

fun isFirstRun(context: Context): Boolean {
    val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isFirstRun = prefs.getBoolean("isFirstRun", true)
    if (isFirstRun) {
        prefs.edit().putBoolean("isFirstRun", false).apply()
    }
    return isFirstRun
}
