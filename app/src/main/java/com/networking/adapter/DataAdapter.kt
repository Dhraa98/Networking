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
import com.networking.R

import com.networking.retrofit.VideoListModel
import com.squareup.picasso.Picasso

class DataAdapter(
    private val context: Context,
    var videoList: List<VideoListModel.DataVideoList>
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tvVideoName: TextView
        var ivThumb: ImageView

        init {
            tvVideoName = itemview.findViewById(R.id.tvVideoName)
            ivThumb = itemview.findViewById(R.id.ivThumb)
        }
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {


        holder.tvVideoName.text = videoList[position].videoName
        val picasso = Picasso.get()
        picasso
            .load(videoList[position].video_image)
            .tag(context)
            .into(holder.ivThumb)


    }

}






