package com.anatoliykichuk.cardiolog.ui.pages.records

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anatoliykichuk.cardiolog.data.FirestoreRepository
import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.anatoliykichuk.cardiolog.ui.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CardioLogViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getRecords() {
        liveData.postValue(AppState.Loading)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            liveData.postValue(
                AppState.Success(FirestoreRepository().getRecords())
            )
        }
    }

    fun addRecord(cardioLog: CardioLog) {
        liveData.postValue(AppState.Loading)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            liveData.postValue(
                AppState.Success(FirestoreRepository().addRecord(cardioLog))
            )
        }
    }
}