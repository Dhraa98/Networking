package com.networking.retrofit




import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitInterface {




    //get Videos
    @POST("get_video_list.php")
    fun getVideosApi(
        @Query("action") action: String,
        @Query("cid") cId: String,
        @Query("language_id") language_id: String
    ): Call<VideoListModel>


}