package com.example.dokku

import android.app.Application
import android.os.Build
import androidx.lifecycle.viewModelScope
import com.example.dokku.api.models.PhotoDetails
import com.example.dokku.ui.viewmodels.PhotoListActivityViewModel
import com.example.dokku.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [Build.VERSION_CODES.O_MR1])

class PhotoListActivityViewModelTest {

    private lateinit var application: Application
    private lateinit var photoListActivityViewModel: PhotoListActivityViewModel

    @Before
    fun setUp() {
        application = Mockito.mock(Application::class.java)
        photoListActivityViewModel = PhotoListActivityViewModel(application)
    }

    private fun getDummyList(): List<PhotoDetails> {
        val data = PhotoDetails("Flock Of birds", created = "2019-01-01T00:00:00Z")
        val list = mutableListOf<PhotoDetails>();
        list.add(data)
        return list
    }

    @Test
    fun isGridLiveDataPopulated() = runBlocking {
        photoListActivityViewModel.onApiDataReceived(Result.Success(getDummyList()))

        val size = photoListActivityViewModel.photoListItemsLD.getOrAwaitValue().size
        assertTrue("photoListLiveData is Empty", size > 0)
    }


}