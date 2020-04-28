package com.example.dokku.ui.viewdata

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dokku.R
import com.example.dokku.api.ApiConstants


class PhotoDetailsViewData {

    var title = MutableLiveData("")
    var photographer = MutableLiveData("")
    var created = MutableLiveData("")
    var imageURL = MutableLiveData("")

    companion object {
        @BindingAdapter("detail_photo")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {

            if(imageUrl.isNotEmpty()){
                val glideUrl = GlideUrl(
                    imageUrl, LazyHeaders.Builder()
                        .addHeader("x-api-key", ApiConstants.API_KEY)
                        .build()
                )

                Glide.with(view.context)
                    .load(glideUrl)
                    .placeholder(R.drawable.placeholder_details)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
            }

        }
    }
}

