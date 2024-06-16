package br.com.fiap.locawebemailapp.repository

import android.location.GnssAntennaInfo.Listener
import androidx.compose.runtime.mutableStateListOf
import br.com.fiap.locawebemailapp.model.Email
import java.time.LocalDate

object EmailRepository {
    private var emails = mutableStateListOf(
        Email(
            "0",
            "Convite para Reunião",
            "Gostaríamos de convidá-lo para a reunião de planejamento do projeto, que ocorrerá na próxima quarta-feira, às 10h, na sala de conferências.\n\nAtenciosamente,\nJoão",
            "ana.silva@email.com",
            "usuario.atual@email.com",
            "Ana Silva",
            LocalDate.parse("2024-05-02"),
            false
        ),
        Email(
            "1",
            "Relatório Mensal",
            "Segue em anexo o relatório mensal das atividades da equipe...",
            "pedro.souza@email.com",
            "usuario.atual@email.com",
            "Pedro Souza",
            LocalDate.parse("2024-05-04"),
            false
        ),
        Email(
            "2",
            "Solicitação de Documentos",
            "Poderia, por favor, enviar a documentação necessária para a renovação do contrato...",
            "mariana.lima@email.com",
            "usuario.atual@email.com",
            "Mariana Lima",
            LocalDate.parse("2024-05-06"),
            false
        )
    )


    fun listarEmailsDesc(): List<Email> = emails.sortedByDescending { it.dataEnvio }

    fun listarEmailsAsc(): List<Email> = emails.sortedBy { it.dataEnvio }

    fun listarEmailsFavoritos(): List<Email> = emails.filter { it.favorito }

    fun listarEmailPorId(id: String): Email? {
        return emails.find{ it.id == id }
    }

    fun listarEmailsPorUser(emails: List<Email>, user: String): List<Email> {
        return emails.filter { it.remetente.startsWith(prefix = user, ignoreCase = true)}
    }

    fun deletarEmailPorId(id: String) {
        emails= emails.apply {
            removeIf { it.id == id }
        }
    }

    fun criarEmail(email: Email) {
        val ultimoEmail = emails.lastOrNull()
        email.id = ((ultimoEmail?.id?.toInt() ?: (0 + 1))).toString()
        email.favorito = false
        emails.add(email)
    }

    fun modificarFavorito(email: Email) {
        email.favorito = !email.favorito
    }


}