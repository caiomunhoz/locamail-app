package br.com.fiap.locawebemailapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "t_email")
data class Email (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var assunto: String = "",
    var mensagem: String = "",
    var emailRemetente: String = "",
    var emailDestinatario: String = "",
    var remetente: String = "",
    var dataEnvio: LocalDate,
    var favorito: Boolean = false
)