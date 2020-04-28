package com.example.dokku.utils

import androidx.lifecycle.MutableLiveData

open class UiState {
    var isRetry = MutableLiveData(false)
    var isLoading = MutableLiveData(false)
    var isDone = MutableLiveData(true)
    var message = MutableLiveData("")

    companion object {
        fun loading(): UiState {
            val uiState = UiState()
            uiState.isLoading.value = true
            uiState.isDone.value = false
            uiState.isRetry.value = false
            uiState.message.value = ""
            return uiState
        }

        fun retry(message: String?): UiState {
            val uiState = UiState()
            uiState.isLoading.value = false
            uiState.isDone.value = false
            uiState.isRetry.value = true
            uiState.message.value = message
            return uiState
        }

        fun done(): UiState {
            val uiState = UiState()
            uiState.isLoading.value = false
            uiState.isDone.value = true
            uiState.isRetry.value = false
            uiState.message.value = ""
            return uiState
        }
    }

}
