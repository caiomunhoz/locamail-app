package br.com.fiap.locawebemailapp.repository

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
            "Ana Silva",
            LocalDate.parse("2024-05-02")
        ),
        Email(
            "1",
            "Relatório Mensal",
            "Segue em anexo o relatório mensal das atividades da equipe...",
            "pedro.souza@email.com",
            "Pedro Souza",
            LocalDate.parse("2024-05-04")
        ),
        Email(
            "2",
            "Solicitação de Documentos",
            "Poderia, por favor, enviar a documentação necessária para a renovação do contrato...",
            "mariana.lima@email.com",
            "Mariana Lima",
            LocalDate.parse("2024-05-06")
        )
    )

    fun listarEmails(): List<Email> = emails

    fun listarEmailPorId(id: String): Email? {
        return emails.find{ it.id == id }
    }

    fun deletarEmailPorId(id: String) {
        emails= emails.apply {
            removeIf { it.id == id }
        }
    }

    fun criarEmail(email: Email) {
        val ultimoEmail = emails.lastOrNull()
        email.id = ((ultimoEmail?.id?.toInt() ?: (0 + 1))).toString()
        emails.add(email)
    }
}