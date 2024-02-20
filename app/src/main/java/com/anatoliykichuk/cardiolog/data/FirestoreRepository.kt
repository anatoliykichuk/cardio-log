package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog
import kotlinx.coroutines.tasks.await

class FirestoreRepository : IRepository {

    private val firestoreClient = FirestoreClient.getClient()

    suspend override fun getRecords(): MutableList<CardioLog> {
        val querySnapshot = firestoreClient.collection(COLLECTION_PATH).get().await()
        return DataConverter.getCardioLogRecordsFromFirestoreDocuments(querySnapshot.documents)
    }

    suspend override fun addRecord(cardioLog: CardioLog): Boolean {
        firestoreClient.collection(COLLECTION_PATH)
            .add(DataConverter.getFirestoreDocumentFromCardioLog(cardioLog))
            .await()
        return true
    }

    suspend override fun updateRecord(cardioLog: CardioLog): Boolean {
        firestoreClient.collection(COLLECTION_PATH)
            .document(cardioLog.id)
            .update(DataConverter.getFirestoreDocumentFromCardioLog(cardioLog))
            .await()
        return true
    }

    suspend override fun removeRecord(cardioLog: CardioLog): Boolean {
        firestoreClient.collection(COLLECTION_PATH)
            .document(cardioLog.id)
            .delete()
            .await()
        return true
    }

    companion object {
        private const val COLLECTION_PATH = "records"
    }
}