package com.anatoliykichuk.cardiolog.ui.state

sealed class AppState {
    data class Success(val responseData: ResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}