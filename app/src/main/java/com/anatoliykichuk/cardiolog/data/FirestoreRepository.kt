package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

class FirestoreRepository : IRepository {

    private val firestoreClient = FirestoreClient.getClient()

    override fun getRecords(): MutableList<CardioLog> {
        var records = mutableListOf <CardioLog>()

        firestoreClient.collection(COLLECTION_PATH)
            .get()
            .addOnSuccessListener {
                val dtoRecords: List<CardioLogDto> = it.toObjects(CardioLogDto::class.java)
                records = DataConverter.getFromDtoRecords(dtoRecords)
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
        return records
    }

    override fun addRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        var records = mutableListOf <CardioLog>()

        val record = HashMap<String, Any>()
        record["id"] = cardioLog.id.toString()
        record["date"] = DataConverter.getFromLocalDateDateToTimestamp(cardioLog.date)
        record["diastolicPressure"] = cardioLog.diastolicPressure
        record["systolicPressure"] = cardioLog.systolicPressure
        record["pulse"] = cardioLog.pulse

        firestoreClient.collection(COLLECTION_PATH)
            .add(record)
            .addOnSuccessListener {
                records = getRecords()
            }
            .addOnFailureListener {
                return@addOnFailureListener
            }
        return records
    }

    override fun updateRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        TODO("Not yet implemented")
    }

    override fun removeRecord(cardioLog: CardioLog): MutableList<CardioLog> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val COLLECTION_PATH = "records"
    }
}