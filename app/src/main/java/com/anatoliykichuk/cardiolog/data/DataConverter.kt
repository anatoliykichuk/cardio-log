package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.google.firebase.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date

object DataConverter {

    fun getRecordsFromCardioLog(cardioLog: CardioLog): HashMap<String, Any> {
        val records = HashMap<String, Any>()
        records["id"] = cardioLog.id
        records["date"] = DataConverter.getFromLocalDateDateToTimestamp(cardioLog.date)
        records["diastolicPressure"] = cardioLog.diastolicPressure
        records["systolicPressure"] = cardioLog.systolicPressure
        records["pulse"] = cardioLog.pulse

        return records
    }

    fun getCardioLogFromDto(cardioLogDto: CardioLogDto): CardioLog {
        return CardioLog(
            diastolicPressure = cardioLogDto.diastolicPressure,
            systolicPressure = cardioLogDto.systolicPressure,
            pulse = cardioLogDto.pulse,
            date = getFromTimestampToLocalDateDate(cardioLogDto.date),
            id = cardioLogDto.id
        )
    }

    fun getCardioLogRecordsFromDto(cardioLogDtoRecords: List<CardioLogDto>): MutableList<CardioLog> {
        val cardioLogRecords: MutableList<CardioLog> = mutableListOf()

        cardioLogDtoRecords.forEach {
            cardioLogRecords.add(getCardioLogFromDto(it))
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