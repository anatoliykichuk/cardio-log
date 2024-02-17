package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

class FirestoreRepository : IRepository {

    private val firestoreClient = FirestoreClient.getClient()
    private var records = mutableListOf<CardioLog>()

    override fun getRecords(): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .get()
            .addOnSuccessListener {
                val dtoRecords: List<CardioLogDto> = it.toObjects(CardioLogDto::class.java)
                records = DataConverter.getCardioLogRecordsFromDto(dtoRecords)
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
        return records
    }

    override fun addRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .add(
                DataConverter.getRecordsFromCardioLog(cardioLog)
            )
            .addOnSuccessListener {
                records = getRecords()
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
        return records
    }

    override fun updateRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .document(cardioLog.id)
            .set(
                DataConverter.getRecordsFromCardioLog(cardioLog)
            )
            .addOnSuccessListener {
                records = getRecords()
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
        return records
    }

    override fun removeRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        firestoreClient.collection(COLLECTION_PATH)
            .document(cardioLog.id)
            .delete()
            .addOnSuccessListener {
                records = getRecords()
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
        return records
    }

    companion object {
        private const val COLLECTION_PATH = "records"
    }
}