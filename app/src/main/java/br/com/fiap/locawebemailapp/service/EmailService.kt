package br.com.fiap.locawebemailapp.service

import br.com.fiap.locawebemailapp.model.EmailDb
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmailService {
    @GET("Email/GetEmails")
    fun listarEmails(): Call<List<EmailDb>>

    @POST("Email/SendEmail")
    fun enviarEmail(@Body emailDb: EmailDb): Call<Void>

    @DELETE("Email/DeleteEmail?id={id}")
    fun deletarEmail(@Path("id") id: Long): Call<Void>

    @PUT("Email/UpdateEmail")
    fun atualizarFavorito(@Body emailDb: EmailDb): Call<Void>
}