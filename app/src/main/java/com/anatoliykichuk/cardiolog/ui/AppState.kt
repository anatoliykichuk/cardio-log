package com.anatoliykichuk.cardiolog.ui

import com.anatoliykichuk.cardiolog.domain.CardioLog

sealed class AppState {
    data class Success(val records: List<CardioLog>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}