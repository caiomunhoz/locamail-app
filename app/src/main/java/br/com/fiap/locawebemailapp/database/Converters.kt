package br.com.fiap.locawebemailapp.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Converters {
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(value: LocalDate?): String? {
        return value?.format(formatter)

    }

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let{
            LocalDate.parse(it, formatter)
        }
    }
}