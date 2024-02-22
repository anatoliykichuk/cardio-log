package com.anatoliykichuk.cardiolog.domain

import java.time.LocalDateTime
import java.util.UUID

data class CardioLog(
    var diastolicPressure: Int = DIASTOLIC_PRESSURE_BY_DEFAULT,
    var systolicPressure: Int = SYSTOLIC_PRESSURE_BY_DEFAULT,
    var pulse: Int = PULSE_BY_DEFAULT,
    val date: LocalDateTime = LocalDateTime.now(),
    val id: String = UUID.randomUUID().toString()
) {
    companion object {
        private const val DIASTOLIC_PRESSURE_BY_DEFAULT = 120
        private const val SYSTOLIC_PRESSURE_BY_DEFAULT = 80
        private const val PULSE_BY_DEFAULT = 70
    }
}
