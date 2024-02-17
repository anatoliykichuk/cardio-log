package com.anatoliykichuk.cardiolog.data

import com.google.firebase.Timestamp

data class CardioLogDto(
    val diastolicPressure: Int,
    val systolicPressure: Int,
    val pulse: Int,
    val date: Timestamp,
    val id: String
) {}
