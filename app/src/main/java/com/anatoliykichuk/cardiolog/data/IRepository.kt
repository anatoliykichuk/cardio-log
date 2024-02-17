package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

interface IRepository {
    fun getRecords(): MutableList<CardioLog>
    fun addRecord(cardioLog: CardioLog): MutableList<CardioLog>
    fun updateRecord(cardioLog: CardioLog): MutableList<CardioLog>
    fun removeRecord(cardioLog: CardioLog): MutableList<CardioLog>
}