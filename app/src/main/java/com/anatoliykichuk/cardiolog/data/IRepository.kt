package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

interface IRepository {
    suspend fun getRecords(): MutableList<CardioLog>
    suspend fun addRecord(cardioLog: CardioLog): Boolean
    suspend fun updateRecord(cardioLog: CardioLog): Boolean
    suspend fun removeRecord(cardioLog: CardioLog): Boolean
}