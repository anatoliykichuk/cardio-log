package com.anatoliykichuk.cardiolog.ui.adapter

import com.anatoliykichuk.cardiolog.domain.CardioLog

interface ICardioLogOnRecordDataChangeListener {
    fun onChanged(cardioLog: CardioLog)
}