package com.networking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.networking.R
import com.networking.databinding.ItemListAdapterBinding

import com.networking.retrofit.VideoListModel
import com.networking.utils.BindingAdapters.setImageUrl
import com.squareup.picasso.Picasso

class DataAdapter(
    private val context: Context,
    var videoList: List<VideoListModel.DataVideoList>
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListAdapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(videoList[position])


    class ViewHolder(val binding: ItemListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(videoList: VideoListModel.DataVideoList) {
            binding.viewModel = videoList
           // setImageUrl(binding.ivThumb, videoList.video_image)
            binding.executePendingBindings()

        }

    }


}








