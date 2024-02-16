package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

class FirestoreRepository : IRepository {

    override fun getRecords(): List<CardioLog> {
        TODO("Not yet implemented")
    }

    override fun addRecord(cardioLog: CardioLog) {
        TODO("Not yet implemented")
    }

    override fun removeRecord(cardioLog: CardioLog) {
        TODO("Not yet implemented")
    }

    private val firestoreClient = FirestoreClient.getClient()
}