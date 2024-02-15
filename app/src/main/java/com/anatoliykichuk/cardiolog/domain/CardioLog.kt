package com.anatoliykichuk.cardiolog.domain

import java.time.LocalDate
import java.util.UUID

data class CardioLog(
    val diastolicPressure: Int,
    val systolicPressure: Int,
    val pulse: Int,
    val date: LocalDate = LocalDate.now(),
    private val id: UUID = UUID.randomUUID()
) {

}
