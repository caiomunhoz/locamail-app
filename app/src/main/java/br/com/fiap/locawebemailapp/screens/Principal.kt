package br.com.fiap.locawebemailapp.screens

import BarraFuncionalidades
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import br.com.fiap.locawebemailapp.components.BarraNavegacao
import br.com.fiap.locawebemailapp.components.EmailCard
import br.com.fiap.locawebemailapp.database.repository.EmailRepository
import br.com.fiap.locawebemailapp.model.Email
import br.com.fiap.locawebemailapp.model.EmailDb
import br.com.fiap.locawebemailapp.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.ZonedDateTime

@Composable
fun Principal(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)
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

    LaunchedEffect(Unit) {
        if (isFirstRun(context)) {
            obterTodosEmailsBD {
                emails -> emailRepository.salvarMultiplos(emails)
                atualizarEmails()
            }
        }
    }

    LaunchedEffect(ordemAscendente, favoritos) {
        atualizarEmails()
    }

    Scaffold(
        bottomBar = { BarraNavegacao(navController) },
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
                    },
                    isDarkTheme = isDarkTheme,
                    onThemeChange = onThemeChange
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    items(emails) { email ->
                        Column {
                            EmailCard(navController, email, isDarkTheme) {
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

fun obterTodosEmailsBD(callback: (List<Email>) -> Unit) {
    val call = RetrofitFactory().getEmailService().listarEmails()
    call.enqueue(object : Callback<List<EmailDb>> {
        override fun onResponse(call: Call<List<EmailDb>>, response: Response<List<EmailDb>>) {
            val emails = mutableListOf<Email>()
            response.body()?.forEach{emailDb ->
                val data = ZonedDateTime.parse(emailDb.dataEnvio).toLocalDate()
                emails.add(Email(
                    assunto = emailDb.assunto,
                    mensagem = emailDb.mensagem,
                    emailRemetente = emailDb.emailRemetente,
                    emailDestinatario = emailDb.emailDestinatario,
                    remetente = emailDb.remetente,
                    dataEnvio = data,
                    favorito = emailDb.favorito
                ))
            }
            callback(emails)
        }

        override fun onFailure(call: Call<List<EmailDb>>, t: Throwable) {
            Log.i("Erro", "$t")
        }
    })
}
