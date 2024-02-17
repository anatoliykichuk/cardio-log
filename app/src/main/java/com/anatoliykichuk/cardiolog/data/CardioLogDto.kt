package com.anatoliykichuk.cardiolog.data

import java.sql.Timestamp

data class CardioLogDto(
    val diastolicPressure: Int,
    val systolicPressure: Int,
    val pulse: Int,
    val date: Timestamp,
    val id: String
) {}
