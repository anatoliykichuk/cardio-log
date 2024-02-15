package com.anatoliykichuk.cardiolog.domain

import java.util.Date

data class CardioLog(
    val date: Date,
    val diastolicPressure: Double,
    val systolicPressure: Double,
    val pulse: Double,
) {

}
