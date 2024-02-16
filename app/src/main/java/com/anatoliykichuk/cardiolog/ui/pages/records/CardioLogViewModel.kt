package com.anatoliykichuk.cardiolog.ui.pages.records

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anatoliykichuk.cardiolog.ui.AppState

class CardioLogViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveData
}