package com.example.dokku.ui.viewdata

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dokku.R
import com.example.dokku.api.ApiConstants


class PhotoListItemViewData {

    var id: String = ""
    var imageURL: String = ""
    var title: String = ""

    companion object {
        @BindingAdapter("photo")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {

            val glideUrl = GlideUrl(
                imageUrl, LazyHeaders.Builder()
                    .addHeader("x-api-key", ApiConstants.API_KEY)
                    .build()
            )

            Glide.with(view.context)
                .load(glideUrl)
                .placeholder(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}

