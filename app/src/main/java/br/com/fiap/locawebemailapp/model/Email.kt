package br.com.fiap.locawebemailapp.model

import java.time.LocalDate

data class Email (
    var id: String = "",
    var assunto: String = "",
    var mensagem: String = "",
    var emailRemetente: String = "",
    var remetente: String = "",
    var dataEnvio: LocalDate,
    var favorito: Boolean = false
)