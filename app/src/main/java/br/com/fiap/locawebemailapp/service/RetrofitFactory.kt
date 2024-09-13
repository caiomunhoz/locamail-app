package br.com.fiap.locawebemailapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val BASE_URL = "https://locawebemailapp.free.beeceptor.com"

    private val retrofitFactory =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getEmailService(): EmailService {
        return retrofitFactory.create(EmailService::class.java)
    }
}