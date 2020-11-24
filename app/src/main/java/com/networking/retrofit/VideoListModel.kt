package com.networking.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


class VideoListModel : Serializable {
    @SerializedName("result")
    @Expose
    var result = 0

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data_video_list")
    @Expose
    var dataVideoList: List<DataVideoList>? = null

    class DataVideoList : Serializable {
      data class  DataVideoList (val name:String, val image:String)
        @SerializedName("result")
        @Expose
        var result: String? = null

        @SerializedName("message")
        @Expose
        var message: String? = null

        @SerializedName("video_category")
        @Expose
        var videoCategory: String? = null

        @SerializedName("video_name")
        @Expose
        var videoName: String? = null

        @SerializedName("video")
        @Expose
        var video: String? = null

        @SerializedName("video_id")
        @Expose
        var video_id: String? = null

        @SerializedName("favourite_id")
        @Expose
        var favourite_id: String? = null

        @SerializedName("video_image")
        @Expose
        var video_image: String? = null

        @SerializedName("isFavVideo")
        @Expose
        var isFavVideo: Boolean? = null

    }
}