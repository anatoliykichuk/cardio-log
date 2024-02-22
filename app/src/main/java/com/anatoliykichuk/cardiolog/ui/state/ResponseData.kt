package com.anatoliykichuk.cardiolog.ui.state

import com.anatoliykichuk.cardiolog.domain.CardioLog

data class ResponseData(
    val records: MutableList<CardioLog>? = null,
    val record: CardioLog? = null,
    val eventType: CardioLogEventType? = null
)


