package br.com.fiap.locawebemailapp.model.mock

import br.com.fiap.locawebemailapp.model.Email
import java.time.LocalDate

fun emailsMockados(): List<Email> {
    return listOf(
        Email(
            assunto = "Convite para Reunião",
            mensagem = "Gostaríamos de convidá-lo para a reunião de planejamento do projeto, que ocorrerá na próxima quarta-feira, às 10h, na sala de conferências.\n\nAtenciosamente,\nAna Silva",
            emailRemetente = "ana.silva@email.com",
            emailDestinatario = "usuario.atual@email.com",
            remetente = "Ana Silva",
            dataEnvio = LocalDate.parse("2024-05-02"),
            favorito = false
        ),
        Email(
            assunto = "Relatório Mensal",
            mensagem = "Segue em anexo o relatório mensal das atividades da equipe. Por favor, revise e envie seus comentários até sexta-feira.\n\nObrigado,\nPedro Souza",
            emailRemetente = "pedro.souza@email.com",
            emailDestinatario = "usuario.atual@email.com",
            remetente = "Pedro Souza",
            dataEnvio = LocalDate.parse("2024-05-04"),
            favorito = false
        ),
        Email(
            assunto = "Solicitação de Documentos",
            mensagem = "Poderia, por favor, enviar a documentação necessária para a renovação do contrato até o final da semana?\n\nGrato,\nMariana Lima",
            emailRemetente = "mariana.lima@email.com",
            emailDestinatario = "usuario.atual@email.com",
            remetente = "Mariana Lima",
            dataEnvio = LocalDate.parse("2024-05-06"),
            favorito = false
        ),
        Email(
            assunto = "Feedback do Projeto",
            mensagem = "Estamos ansiosos para receber seu feedback sobre o novo projeto. Sua opinião é muito importante para nós.\n\nAtenciosamente,\nFernando Bernardes",
            emailRemetente = "fernando.bernardes@email.com",
            emailDestinatario = "usuario.atual@email.com",
            remetente = "Fernando Bernardes",
            dataEnvio = LocalDate.parse("2024-06-07"),
            favorito = false
        ),
        Email(
            assunto = "Convite para Evento",
            mensagem = "Gostaríamos de convidá-lo para o nosso evento anual de networking, que acontecerá no dia 25 de junho, às 18h, no Hotel Central. Esperamos contar com sua presença.\n\nAbraços,\nLuana Amorim",
            emailRemetente = "luana.amorim@email.com",
            emailDestinatario = "usuario.atual@email.com",
            remetente = "Luana Amorim",
            dataEnvio = LocalDate.parse("2024-06-08"),
            favorito = false
        )
    )
}