package br.com.fiap.locawebemailapp.model

data class EmailDb (
    var assunto: String = "",
    var mensagem: String = "",
    var emailRemetente: String = "",
    var emailDestinatario: String = "",
    var remetente: String = "",
    var dataEnvio: String = "",
    var favorito: Boolean = false
)