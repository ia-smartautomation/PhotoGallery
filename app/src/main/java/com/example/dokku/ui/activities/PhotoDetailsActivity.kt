package com.example.dokku.ui.activities

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.core.animation.addListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dokku.R
import com.example.dokku.databinding.ActivityPhotoDetailsBinding
import com.example.dokku.ui.viewmodels.PhotoDetailsActivityViewModel
import kotlinx.coroutines.NonCancellable.start


class PhotoDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityPhotoDetailsBinding
    lateinit var viewModel: PhotoDetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photoId = intent?.getStringExtra("id") ?: ""

        viewModel = ViewModelProvider(
            this,
            PhotoDetailsActivityViewModel.ViewModelFactory(application, photoId)
        ).get(PhotoDetailsActivityViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_details)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.data = viewModel.viewData

        if (!viewModel.apiCalled) {
            viewModel.loadPhotoDetails()
        }

        initAnimations()
    }

    private fun initAnimations() {
        binding.viewDetails?.setOnClickListener {
            val animShow = ObjectAnimator.ofFloat(binding.detailsSection, "translationY", 0f).apply {
                duration = 300
            }

            animShow.addListener(onStart = {
                ObjectAnimator.ofFloat(binding.viewDetails, "alpha", 0f).apply {
                    duration = 300
                    start()
                }
            })
            animShow.start()
        }

        binding.close?.setOnClickListener {
            val sectionHeight:Float = binding.detailsSection?.height?.toFloat()!!
            val animHide = ObjectAnimator.ofFloat(binding.detailsSection, "translationY", sectionHeight).apply {
                duration = 300
            }

            animHide.addListener(onEnd = {
                ObjectAnimator.ofFloat(binding.viewDetails, "alpha", 1f).apply {
                    duration = 300
                    start()
                }
            })
            animHide.start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransitionExit()
    }

}
