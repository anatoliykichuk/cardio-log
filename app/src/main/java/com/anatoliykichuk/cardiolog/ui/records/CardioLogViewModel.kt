package com.anatoliykichuk.cardiolog.ui.records

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anatoliykichuk.cardiolog.data.FirestoreRepository
import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.anatoliykichuk.cardiolog.ui.state.AppState
import com.anatoliykichuk.cardiolog.ui.state.CardioLogEventType
import com.anatoliykichuk.cardiolog.ui.state.ResponseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CardioLogViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val repository = FirestoreRepository()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getRecords() {
        liveData.postValue(AppState.Loading)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(records = repository.getRecords())
                    )
                )
            } catch (error: Throwable) {
                liveData.postValue(AppState.Error(error))
            }
        }
    }

    fun updateRecord(cardioLog: CardioLog) {
        liveData.postValue(AppState.Loading)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(
                            record = repository.updateRecord(cardioLog),
                            eventType = CardioLogEventType.UPDATING)
                    )
                )
            } catch (error: Throwable) {
                liveData.postValue(AppState.Error(error))
            }
        }
    }

    fun addRecord(cardioLog: CardioLog) {
        liveData.postValue(AppState.Loading)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(
                            record = repository.addRecord(cardioLog),
                            eventType = CardioLogEventType.ADDING)
                    )
                )
            } catch (error: Throwable) {
                liveData.postValue(AppState.Error(error))
            }
        }
    }

    fun removeRecord(cardioLog: CardioLog) {
        liveData.postValue(AppState.Loading)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            try {
                liveData.postValue(
                    AppState.Success(
                        ResponseData(
                            record = repository.removeRecord(cardioLog),
                            eventType = CardioLogEventType.REMOVING
                        )
                    )
                )
            } catch (error: Throwable) {
                liveData.postValue(AppState.Error(error))
            }
        }
    }
}