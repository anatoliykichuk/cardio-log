package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date

object DataConverter {

    fun getFirestoreDocumentFromCardioLog(cardioLog: CardioLog): HashMap<String, Any> {
        val document = HashMap<String, Any>()
        document["id"] = cardioLog.id
        document["date"] = DataConverter.getFromLocalDateDateToTimestamp(cardioLog.date)
        document["diastolicPressure"] = cardioLog.diastolicPressure
        document["systolicPressure"] = cardioLog.systolicPressure
        document["pulse"] = cardioLog.pulse

        return document
    }

    fun getCardioLogFromFirestoreDocument(document: Map<String, Any>): CardioLog {
        return CardioLog(
            diastolicPressure = (document["diastolicPressure"] as Long).toInt(),
            systolicPressure = (document["systolicPressure"] as Long).toInt(),
            pulse = (document["pulse"] as Long).toInt(),
            date = getFromTimestampToLocalDateDate(document["date"] as Timestamp),
            id = document["id"] as String
        )
    }

    fun getCardioLogRecordsFromFirestoreDocuments(
        documents: MutableList<DocumentSnapshot>
    ): MutableList<CardioLog> {
        val cardioLogRecords: MutableList<CardioLog> = mutableListOf()

        for (document in documents) {
            document.data?.let {
                cardioLogRecords.add(getCardioLogFromFirestoreDocument(it))
            }
        }
        return cardioLogRecords
    }

    fun getFromLocalDateDateToTimestamp(localDateTime: LocalDateTime): Timestamp {
        return Timestamp(
            Date.from(localDateTime.toInstant(ZoneOffset.UTC))
        )
    }

    fun getFromTimestampToLocalDateDate(timestamp: Timestamp): LocalDateTime {
        return LocalDateTime.ofInstant(
            timestamp.toDate().toInstant(),
            ZoneId.systemDefault()
        )
    }
}