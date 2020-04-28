package com.example.dokku.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dokku.api.ApiConstants
import com.example.dokku.repository.PhotoRepository
import com.example.dokku.api.models.PhotoDetails
import com.example.dokku.utils.Result
import com.example.dokku.utils.UiState
import com.example.dokku.ui.viewdata.PhotoListItemViewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoListActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val photoRepository = PhotoRepository(application)
    var photoListItemsLD: MutableLiveData<List<PhotoListItemViewData>> = MutableLiveData(
        mutableListOf()
    );
    var uiState = MutableLiveData(UiState.done())
    var apiCalled = false;

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            updateUiState(UiState.loading())
            onApiDataReceived(photoRepository.getPhotos(1))
        }
    }

     suspend fun onApiDataReceived(result: Result<List<PhotoDetails>>) {
        when (result) {
            is Result.Success -> {
                photoListItemsLD.value = convertPhotoListToViewData(result.data)
                updateUiState(UiState.done())
            }
            is Result.Unsuccess -> {
                updateUiState(UiState.retry(result.code.toString()))
            }
            is Result.Error -> {
                updateUiState(UiState.retry(result.message))
            }
        }
        apiCalled = true;
    }

    suspend fun updateUiState(updatedUiState: UiState) = withContext(Dispatchers.Main) {
        uiState.value = updatedUiState
    }

    private fun convertPhotoListToViewData(photoList: List<PhotoDetails>): List<PhotoListItemViewData> {

        val viewDataList = mutableListOf<PhotoListItemViewData>();
        for (photo in photoList) {
            val photoListItemViewData = PhotoListItemViewData();
            photoListItemViewData.id = photo.id
            photoListItemViewData.imageURL = ApiConstants.getThumbnailUrl(photo.id)
            photoListItemViewData.title = photo.title
            viewDataList.add(photoListItemViewData)
        }
        return viewDataList;
    }

}