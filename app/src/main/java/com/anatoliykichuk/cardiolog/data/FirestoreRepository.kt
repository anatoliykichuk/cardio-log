package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog
import kotlinx.coroutines.tasks.await

class FirestoreRepository : IRepository {

    private val firestoreClient = FirestoreClient.getClient()

    suspend override fun getRecords(): MutableList<CardioLog> {
        val querySnapshot = firestoreClient.collection(COLLECTION_PATH).get().await()
        return DataConverter.getCardioLogRecordsFromFirestoreDocuments(querySnapshot.documents)
    }

    suspend override fun addRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .add(DataConverter.getFirestoreDocumentFromCardioLog(cardioLog))
            .await()
        return getRecords()
    }

    suspend override fun updateRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .document(cardioLog.id)
            .set(DataConverter.getFirestoreDocumentFromCardioLog(cardioLog))
            .await()
        return getRecords()
    }

    suspend override fun removeRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .document(cardioLog.id)
            .delete()
            .await()
        return getRecords()
    }

    companion object {
        private const val COLLECTION_PATH = "records"
    }
}