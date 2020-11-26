package com.networking.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.networking.data.DataRepository
import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.VideoListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class MainActivityViewModel (application: Application) : AndroidViewModel(application) {
    val userRepository=DataRepository()
    var progressVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val userData : LiveData<VideoListModel> = userRepository.getDataCall(progressVisibility)
   /* var dataValue: MutableLiveData<VideoListModel> = MutableLiveData()
    var progressVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getDataCall(context: Context) {


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

                    Toast.makeText(
                        context,
                        response!!.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                progressVisibility.value = false
            }

            override fun onFailure(call: Call<VideoListModel>?, t: Throwable?) {
                progressVisibility.value = false
                Toast.makeText(context, t!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
*/
}