package com.networking.viewModel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.VideoListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var dataValue: MutableLiveData<VideoListModel> = MutableLiveData()
    private val mutableProgress = MediatorLiveData<Boolean>()
    val progress: MediatorLiveData<Boolean> = mutableProgress

     fun getDataCall(context: Context) {
         mutableProgress.value = true
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

                    dataValue.value=response.body()
                    mutableProgress.value = false
                   /* val movies: List<VideoListModel.DataVideoList> =
                        response.body()!!.dataVideoList!!

                    initDataList(movies)*/


                } else {
                    mutableProgress.value = false
                    Toast.makeText(
                        context,
                        response!!.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<VideoListModel>?, t: Throwable?) {
                mutableProgress.value = false
                Toast.makeText(context, t!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}