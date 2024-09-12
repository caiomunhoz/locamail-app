package br.com.fiap.locawebemailapp.database.repository

import android.content.Context
import br.com.fiap.locawebemailapp.database.dao.EmailDb
import br.com.fiap.locawebemailapp.model.Email

class EmailRepository(context: Context) {
    private val db = EmailDb.getDatabase(context).emailDao()

    fun salvar(email: Email): Long {
        return db.salvar(email)
    }

    fun salvarMultiplos(emails: List<Email>) {
        return db.salvarMultiplos(emails)
    }

    fun modificarFavorito(email: Email): Int {
        return db.modificarFavorito(email)
    }

    fun deletar(email: Email) {
        db.deletar(email)
    }

    fun buscarEmailPorId(id: Int): Email {
        return db.buscarEmailPorId(id)
    }

    fun listarEmailsDesc(): List<Email> {
        return db.buscarTodosDesc()
    }

    fun listarEmailsAsc(): List<Email> {
        return db.buscarTodosAsc()
    }

    fun listarFavoritos(): List<Email> {
        return db.buscarFavoritos()
    }

    fun listarPorBusca(busca: String): List<Email> {
        return db.buscarNoEmail("%${busca}%")
    }
}