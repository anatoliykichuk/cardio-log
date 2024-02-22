package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

interface IRepository {
    suspend fun getRecords(): MutableList<CardioLog>
    suspend fun addRecord(cardioLog: CardioLog): CardioLog
    suspend fun updateRecord(cardioLog: CardioLog): CardioLog
    suspend fun removeRecord(cardioLog: CardioLog): CardioLog
}