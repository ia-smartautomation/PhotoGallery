package com.example.dokku.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.dokku.api.ApiConstants
import com.example.dokku.api.models.PhotoDetails
import com.example.dokku.repository.PhotoRepository
import com.example.dokku.ui.viewdata.PhotoDetailsViewData
import com.example.dokku.utils.Result
import com.example.dokku.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


class PhotoDetailsActivityViewModel(application: Application, val photoId: String) :
    AndroidViewModel(application) {

    val viewData: PhotoDetailsViewData = PhotoDetailsViewData()
    private val photoRepository = PhotoRepository(application)
    var uiState = MutableLiveData(UiState.done())
    var apiCalled = false

    fun loadPhotoDetails() {

        viewModelScope.launch {
            updateUiState(UiState.loading())
            when (val result = photoRepository.getPhotoDetails(photoId)) {
                is Result.Success -> {
                    updateViewData(result.data)
                    updateUiState(UiState.done())
                }
                is Result.Unsuccess -> {
                    updateUiState(UiState.retry(result.code.toString()))
                }
                is Result.Error -> {
                    updateUiState(UiState.retry(result.message))
                }
            }
            apiCalled = true
        }
    }

    private suspend fun updateViewData(photoDetails: PhotoDetails) = withContext(Dispatchers.Main) {
        viewData.title.value = photoDetails.title.trim()
        viewData.photographer.value = photoDetails.photographer
        viewData.imageURL.value = ApiConstants.getPhotoUrl(photoDetails.id)
        viewData.created.value = changeDateFormat(photoDetails.created)

    }

    fun changeDateFormat(date: String): String {

        val offsetDateTime = OffsetDateTime.parse(
            date,
            DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssX", Locale.ROOT)
        )
        val localDateTime = offsetDateTime.toLocalDateTime()
        val displayFormat = localDateTime.format(DateTimeFormatter.ofPattern("EEE, d MMM uuuu"))

        return displayFormat
    }

    private suspend fun updateUiState(updatedUiState: UiState) = withContext(Dispatchers.Main) {
        uiState.value = updatedUiState
    }

    class ViewModelFactory(val application: Application, private val photoId: String) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PhotoDetailsActivityViewModel(application, photoId) as T
        }
    }
}