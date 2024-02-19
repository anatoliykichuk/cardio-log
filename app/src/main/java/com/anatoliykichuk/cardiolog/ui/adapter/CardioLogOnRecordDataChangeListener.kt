package com.anatoliykichuk.cardiolog.ui.adapter

import com.anatoliykichuk.cardiolog.domain.CardioLog

interface CardioLogOnRecordDataChangeListener {
    fun onChanged(cardioLog: CardioLog)
}