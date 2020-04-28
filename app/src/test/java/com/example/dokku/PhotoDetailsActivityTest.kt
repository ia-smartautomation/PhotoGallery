package com.example.dokku

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dokku.api.models.PhotoDetails
import com.example.dokku.ui.activities.PhotoDetailsActivity
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [Build.VERSION_CODES.O_MR1])

class PhotoDetailsActivityTest {

    @Rule
    @JvmField
    var testRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var activityController: ActivityController<PhotoDetailsActivity>
    private lateinit var activity: PhotoDetailsActivity


    @Before
    @Throws(Exception::class)
    fun setUp() = runBlocking {
        activityController = Robolectric.buildActivity(PhotoDetailsActivity::class.java)

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
    fun isDetailsSetInUI() = runBlocking {
        val photoDetails = getDummyData()
        activity.viewModel.viewData.title.value = photoDetails.title
        activity.viewModel.viewData.created.value = photoDetails.created
        activity.viewModel.viewData.photographer.value = photoDetails.photographer


        assertTrue(
            "Title is not set",
            activity.binding.title.text.toString().equals(photoDetails.title)
        )

        assertTrue(
            "Date is not set",
            activity.binding.date.text.toString().equals(photoDetails.created)
        )

        assertTrue(
            "Photographer is not set",
            activity.binding.photographer.text.toString().equals(photoDetails.photographer)
        )
    }

    private fun getDummyData(): PhotoDetails {
        return PhotoDetails("Flock Of birds", "Unknown", created = "2019-01-01T00:00:00Z")
    }

}