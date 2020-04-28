package com.example.dokku.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dokku.R
import com.example.dokku.databinding.ItemPhotoBinding
import com.example.dokku.ui.viewdata.PhotoListItemViewData


class PhotoAdapter : ListAdapter<PhotoListItemViewData, PhotoAdapter.RecyclerViewHolder>(object :
    DiffUtil.ItemCallback<PhotoListItemViewData>() {

    override fun areItemsTheSame(oldItem: PhotoListItemViewData, newItem: PhotoListItemViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoListItemViewData, newItem: PhotoListItemViewData): Boolean {
        return oldItem == newItem
    }
}) {

    lateinit var click: (id: String) -> Unit

    fun initCallBack(click: (id: String) -> Unit) {
        this.click = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photo,
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecyclerViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataModel: PhotoListItemViewData) {
            binding.data = dataModel
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                click(dataModel.id)
            }
        }
    }

}