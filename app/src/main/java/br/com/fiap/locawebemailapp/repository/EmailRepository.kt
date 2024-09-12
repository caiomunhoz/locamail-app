//package br.com.fiap.locawebemailapp.repository
//
//import androidx.compose.runtime.mutableStateListOf
//import br.com.fiap.locawebemailapp.model.Email
//import java.time.LocalDate
//
//object EmailRepository {
//    private var emails = mutableStateListOf(
//        Email(
//            "0",
//            "Convite para Reunião",
//            "Gostaríamos de convidá-lo para a reunião de planejamento do projeto, que ocorrerá na próxima quarta-feira, às 10h, na sala de conferências.\n\nAtenciosamente,\nAna Silva",
//            "ana.silva@email.com",
//            "usuario.atual@email.com",
//            "Ana Silva",
//            LocalDate.parse("2024-05-02"),
//            false
//        ),
//        Email(
//            "1",
//            "Relatório Mensal",
//            "Segue em anexo o relatório mensal das atividades da equipe. Por favor, revise e envie seus comentários até sexta-feira.\n\nObrigado,\nPedro Souza",
//            "pedro.souza@email.com",
//            "usuario.atual@email.com",
//            "Pedro Souza",
//            LocalDate.parse("2024-05-04"),
//            false
//        ),
//        Email(
//            "2",
//            "Solicitação de Documentos",
//            "Poderia, por favor, enviar a documentação necessária para a renovação do contrato até o final da semana?\n\nGrato,\nMariana Lima",
//            "mariana.lima@email.com",
//            "usuario.atual@email.com",
//            "Mariana Lima",
//            LocalDate.parse("2024-05-06"),
//            false
//        ),
//        Email(
//            "3",
//            "Feedback do Projeto",
//            "Estamos ansiosos para receber seu feedback sobre o novo projeto. Sua opinião é muito importante para nós.\n\nAtenciosamente,\nFernando Bernardes",
//            "fernando.bernardes@email.com",
//            "usuario.atual@email.com",
//            "Fernando Bernardes",
//            LocalDate.parse("2024-06-07"),
//            false
//        ),
//        Email(
//            "4",
//            "Convite para Evento",
//            "Gostaríamos de convidá-lo para o nosso evento anual de networking, que acontecerá no dia 25 de junho, às 18h, no Hotel Central. Esperamos contar com sua presença.\n\nAbraços,\nLuana Amorim",
//            "luana.amorim@email.com",
//            "usuario.atual@email.com",
//            "Luana Amorim",
//            LocalDate.parse("2024-06-08"),
//            false
//        )
//    )
//
//
//    fun listarEmailsDesc(): List<Email> = emails.sortedByDescending { it.dataEnvio }
//
//    fun listarEmailsAsc(): List<Email> = emails.sortedBy { it.dataEnvio }
//
//    fun listarEmailsFavoritos(): List<Email> = emails.filter { it.favorito }
//
//    fun listarEmailPorId(id: String): Email? {
//        return emails.find{ it.id == id }
//    }
//
//    fun listarEmailsPorUser(emails: List<Email>, user: String): List<Email> {
//        return emails.filter { it.remetente.startsWith(prefix = user, ignoreCase = true)}
//    }
//
//    fun deletarEmailPorId(id: String) {
//        emails= emails.apply {
//            removeIf { it.id == id }
//        }
//    }
//
//    fun criarEmail(email: Email) {
//        val ultimoEmail = emails.lastOrNull()
//        email.id = (ultimoEmail!!.id.toInt() + 1).toString()
//        email.favorito = false
//        emails.add(email)
//    }
//
//    fun modificarFavorito(email: Email) {
//        email.favorito = !email.favorito
//        println(email.favorito)
//    }
//
//
//}