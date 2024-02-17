package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.google.firebase.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date

object DataConverter {

    fun fromDto(cardioLogDto: CardioLogDto): CardioLog {
        return CardioLog(
            diastolicPressure = cardioLogDto.diastolicPressure,
            systolicPressure = cardioLogDto.systolicPressure,
            pulse = cardioLogDto.pulse,
            date = fromTimestampToLocalDateDate(cardioLogDto.date),
            id = cardioLogDto.id
        )
    }

    fun getFromDtoRecords(cardioLogDtoRecords: List<CardioLogDto>): MutableList<CardioLog> {
        val cardioLogRecords: MutableList<CardioLog> = mutableListOf()

        cardioLogDtoRecords.forEach {
            cardioLogRecords.add(fromDto(it))
        }
        return cardioLogRecords
    }

    fun getFromLocalDateDateToTimestamp(localDateTime: LocalDateTime): Timestamp {
        return Timestamp(
            Date.from(localDateTime.toInstant(ZoneOffset.UTC))
        )
    }

    fun fromTimestampToLocalDateDate(timestamp: Timestamp): LocalDateTime {
        return LocalDateTime.ofInstant(
            timestamp.toDate().toInstant(),
            ZoneId.systemDefault()
        )
    }
}