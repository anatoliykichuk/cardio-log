package com.anatoliykichuk.cardiolog.domain

import java.time.LocalDateTime
import java.util.UUID

data class CardioLog(
    val diastolicPressure: Int,
    val systolicPressure: Int,
    val pulse: Int,
    val date: LocalDateTime = LocalDateTime.now(),
    private val id: UUID = UUID.randomUUID()
) {

}
