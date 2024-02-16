package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

interface IRepository {
    fun getRecords(): List<CardioLog>
    fun addRecord(cardioLog: CardioLog)
    fun removeRecord(cardioLog: CardioLog)
}