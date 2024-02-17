package com.anatoliykichuk.cardiolog.data

import com.anatoliykichuk.cardiolog.domain.CardioLog

object CardioLogConverter {

    fun fromDto(cardioLogDto: CardioLogDto): CardioLog {
        return CardioLog(
            cardioLogDto.diastolicPressure.toInt(),
            cardioLogDto.systolicPressure.toInt(),
            cardioLogDto.pulse.toInt()
        )
    }

    fun fromDtoRecords(cardioLogDtoRecords: List<CardioLogDto>): MutableList<CardioLog> {
        val cardioLogRecords: MutableList<CardioLog> = mutableListOf()

        cardioLogDtoRecords.forEach {
            cardioLogRecords.add(fromDto(it))
        }
        return cardioLogRecords
    }
}