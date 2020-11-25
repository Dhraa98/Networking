package com.networking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.networking.databinding.ItemListAdapterBinding
import com.networking.retrofit.VideoListModel


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
            binding.executePendingBindings()

        }

    }


}








