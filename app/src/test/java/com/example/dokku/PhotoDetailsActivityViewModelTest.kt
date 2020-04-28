package com.example.dokku

import android.app.Application
import android.os.Build
import androidx.lifecycle.viewModelScope
import com.example.dokku.api.models.PhotoDetails
import com.example.dokku.ui.viewmodels.PhotoDetailsActivityViewModel
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

class PhotoDetailsActivityViewModelTest {

    private lateinit var application: Application
    private lateinit var photoDetailsActivityViewModel: PhotoDetailsActivityViewModel

    @Before
    fun setUp() {
        application = Mockito.mock(Application::class.java)
        photoDetailsActivityViewModel = PhotoDetailsActivityViewModel(application, "")
    }

    @Test
    fun isDateDisplayFormatValid() = runBlocking {
        val date = "2020-04-01T00:00:00Z"
        val formattedDate = photoDetailsActivityViewModel.changeDateFormat(date)
        val expected = "Wed, 1 Apr 2020"
        assertTrue("Invalid format", formattedDate == expected)
    }


}