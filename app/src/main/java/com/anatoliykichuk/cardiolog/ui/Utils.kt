package com.anatoliykichuk.cardiolog.ui

import java.time.LocalDateTime

object Utils {

    fun getDateFormatted(date: LocalDateTime): String {
        return "${date.dayOfMonth} ${date.month.name.lowercase()} ${date.year}"
    }

    fun getTimeFormatted(time: LocalDateTime): String {
        return "${time.hour}:${time.minute}"
    }
}