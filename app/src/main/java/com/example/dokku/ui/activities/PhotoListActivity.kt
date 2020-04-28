package com.example.dokku.ui.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dokku.R
import com.example.dokku.ui.adapters.PhotoAdapter
import com.example.dokku.databinding.ActivityPhotosListBinding
import com.example.dokku.ui.viewmodels.PhotoListActivityViewModel

class PhotoListActivity : BaseActivity() {

    lateinit var binding: ActivityPhotosListBinding
    lateinit var photoListActivityViewModel: PhotoListActivityViewModel
    lateinit var photoAdapter: PhotoAdapter
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos_list)

        context = this

        photoListActivityViewModel = ViewModelProvider(this).get(PhotoListActivityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = photoListActivityViewModel

        initRecyclerView()
        initObservers()

        if (!photoListActivityViewModel.apiCalled)
            photoListActivityViewModel.loadPhotos()
    }

    private fun initObservers() {
        photoListActivityViewModel.photoListItemsLD.observe(this, Observer { data ->
            photoAdapter.submitList(data)
        })
    }

    private fun initRecyclerView() {

        photoAdapter = PhotoAdapter()

        photoAdapter.initCallBack { id: String ->
            val detailIntent = Intent(context, PhotoDetailsActivity::class.java)
            detailIntent.putExtra("id", id)
            startActivity(detailIntent)
        }

        val recyclerView = binding.photoList

        var columnCount = 2
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columnCount = 4
        }
        recyclerView.layoutManager = GridLayoutManager(this, columnCount)

        recyclerView.adapter = photoAdapter
        recyclerView.setHasFixedSize(true)

    }

}
