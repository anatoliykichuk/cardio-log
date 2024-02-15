package com.anatoliykichuk.cardiolog.domain

import java.util.Date

data class CardioLog(
    val date: Date,
    val diastolicPressure: Int,
    val systolicPressure: Int,
    val pulse: Int,
) {

}
