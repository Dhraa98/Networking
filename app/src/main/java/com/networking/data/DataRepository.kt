package com.networking.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.VideoListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository {

    var dataValue: MutableLiveData<VideoListModel> = MutableLiveData()
   // var progressVisibility: MutableLiveData<Boolean> = MutableLiveData(false)


    fun getDataCall(progressVisibility: MutableLiveData<Boolean>): LiveData<VideoListModel> {


        progressVisibility.value = true

        val call: Call<VideoListModel> =
            RetrofitClass.getClient.getVideosApi("get", "", "")

        call.enqueue(object : Callback<VideoListModel> {

            override fun onResponse(
                call: Call<VideoListModel>?,
                response: Response<VideoListModel>?
            ) {

                if (response != null && response!!.code() == (200) && response.body()!!.dataVideoList!!.size > 0 && !response.body()!!.dataVideoList!!.get(
                        0
                    ).result.equals("0")
                ) {

                    dataValue.value = response.body()

                } else {

                    /*Toast.makeText(
                        context,
                        response!!.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()*/
                }
                progressVisibility.value = false
            }

            override fun onFailure(call: Call<VideoListModel>?, t: Throwable?) {
                progressVisibility.value = false
                //  Toast.makeText(context, t!!.message, Toast.LENGTH_SHORT).show()
            }
        })
        return dataValue
    }
}