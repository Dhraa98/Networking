package com.networking.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.networking.R
import com.networking.databinding.ItemListAdapterBinding

import com.networking.retrofit.VideoListModel
import com.squareup.picasso.Picasso

class DataAdapter(
    private val context: Context,
    var videoList: List<VideoListModel.DataVideoList>
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListAdapterBinding.inflate(inflater)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class ViewHolder(val binding: ItemListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
       /* fun bind(videoList: VideoListModel.DataVideoList) {
            with(binding) {
                tvVideoName.text = videoList.videoName
                ivThumb.load(videoList.video_image) {
                    crossfade(true)

                }
            }

            }*/
       /* var tvVideoName: TextView
        var ivThumb: ImageView

        init {
            tvVideoName = itemview.findViewById(R.id.tvVideoName)
            ivThumb = itemview.findViewById(R.id.ivThumb)
        }*/
    }


    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int){
        holder.binding.tvVideoName.text = videoList[position].videoName
        holder.binding.ivThumb.load(videoList[position].video_image) {
            crossfade(true)

        }
    }





    }








