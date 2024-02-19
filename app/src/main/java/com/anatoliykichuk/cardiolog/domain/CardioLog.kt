package com.anatoliykichuk.cardiolog.domain

import java.time.LocalDateTime
import java.util.UUID

data class CardioLog(
    var diastolicPressure: Int = diastolicPressureByDefault,
    var systolicPressure: Int = systolicPressureByDefault,
    var pulse: Int = pulseByDefault,
    val date: LocalDateTime = LocalDateTime.now(),
    val id: String = UUID.randomUUID().toString()
) {
    companion object {
        private const val diastolicPressureByDefault = 120
        private const val systolicPressureByDefault = 80
        private const val pulseByDefault = 70
    }
}
