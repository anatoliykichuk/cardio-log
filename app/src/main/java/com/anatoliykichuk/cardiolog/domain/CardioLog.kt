package com.anatoliykichuk.cardiolog.domain

import java.time.LocalDateTime
import java.util.UUID

data class CardioLog(
    val diastolicPressure: Int = diastolicPressureByDefault,
    val systolicPressure: Int = systolicPressureByDefault,
    val pulse: Int = pulseByDefault,
    val date: LocalDateTime = LocalDateTime.now(),
    val id: UUID = UUID.randomUUID()
) {
    companion object {
        private const val diastolicPressureByDefault = 120
        private const val systolicPressureByDefault = 80
        private const val pulseByDefault = 70
    }
}
