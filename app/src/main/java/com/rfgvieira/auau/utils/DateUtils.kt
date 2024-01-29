package com.rfgvieira.auau.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {

        fun Long.toDateFormat(pattern: String = "dd/MM/yyyy"): String {
            val date = Date(this)
            val formatter = SimpleDateFormat(pattern, Locale("pt-br"))
            return formatter.format(date)
        }

        fun String.toYear(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                yearNew(this).toString()
            else
                yearOld(this).toString()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun yearNew(date: String): Int {
            val parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            return Period.between(parsedDate, LocalDate.now()).years
        }

        fun yearOld(date: String): Int {

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt-br"))
            val parsedDate = formatter.parse(date)

            val birth = Calendar.getInstance()
            val currentDate = Calendar.getInstance()

            if (parsedDate != null) {
                birth.time = parsedDate
            }

            val year = birth.get(Calendar.YEAR)
            val month = birth.get(Calendar.MONTH)
            val day = birth.get(Calendar.DAY_OF_MONTH)

            birth.set(year, month + 1, day)

            var age = currentDate.get(Calendar.YEAR) - birth.get(Calendar.YEAR)

            if (currentDate.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR))
                age--

            return age
        }
    }
}