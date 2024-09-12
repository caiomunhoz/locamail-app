package br.com.fiap.locawebemailapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.locawebemailapp.model.Email

@Dao
interface EmailDao {
    @Insert
    fun salvar(email: Email): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun salvarMultiplos(emails: List<Email>)

    @Delete
    fun deletar(email: Email): Int

    @Update
    fun modificarFavorito(email: Email): Int

    @Query("SELECT * FROM t_email WHERE id = :id")
    fun buscarEmailPorId(id: Int): Email

    @Query("SELECT * FROM t_email ORDER BY dataEnvio DESC")
    fun buscarTodosDesc(): List<Email>

    @Query("SELECT * FROM t_email ORDER BY dataEnvio ASC")
    fun buscarTodosAsc(): List<Email>

    @Query("SELECT * FROM t_email WHERE favorito = 1")
    fun buscarFavoritos(): List<Email>

    @Query("SELECT * FROM t_email WHERE " +
            "assunto LIKE :busca " +
            "OR mensagem LIKE :busca " +
            "OR emailRemetente LIKE :busca " +
            "OR emailDestinatario LIKE :busca " +
            "OR remetente LIKE :busca")
    fun buscarNoEmail(busca: String): List<Email>
}