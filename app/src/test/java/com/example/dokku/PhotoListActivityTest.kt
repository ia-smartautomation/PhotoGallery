package com.example.dokku

import android.app.Application
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.UiThread
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.lifecycleScope
import com.example.dokku.ui.activities.PhotoDetailsActivity
import com.example.dokku.ui.activities.PhotoListActivity
import com.example.dokku.ui.viewdata.PhotoListItemViewData
import com.example.dokku.utils.UiState
import junit.framework.Assert.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [Build.VERSION_CODES.O_MR1])

class PhotoListActivityTest {

    @Rule
    @JvmField
    var testRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activityController: ActivityController<PhotoListActivity>
    private lateinit var activity: PhotoListActivity


    @Before
    @Throws(Exception::class)
    fun setUp() = runBlocking {
        activityController = Robolectric.buildActivity(PhotoListActivity::class.java)

        Robolectric.getForegroundThreadScheduler().pause();
        activityController.setup()
        Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();
        Robolectric.getForegroundThreadScheduler().unPause();

        activity = activityController.get()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    fun isRetrySectionShown() = runBlocking {
        activity.photoListActivityViewModel.updateUiState(UiState.retry(""))

        assertTrue(
            "Retry Section is not shown",
            activity.binding.retrySection.visibility == View.VISIBLE
        )
    }

    @Test
    fun isLoadingSectionShown() = runBlocking {
        activity.photoListActivityViewModel.updateUiState(UiState.loading())

        assertTrue(
            "Loading Section is not shown",
            activity.binding.loadingSection.visibility == View.VISIBLE
        )
    }


    fun getDummyList(): List<PhotoListItemViewData> {
        val data = PhotoListItemViewData()
        data.id = "1"
        data.title = "Flock Of birds"
        val list = mutableListOf<PhotoListItemViewData>();
        list.add(data)
        return list
    }

}