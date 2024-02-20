package com.anatoliykichuk.cardiolog.ui

import com.anatoliykichuk.cardiolog.domain.CardioLog

data class ResponseData(
    val records: MutableList<CardioLog>? = null,
    val recordsAreUpdated: Boolean = true
)
