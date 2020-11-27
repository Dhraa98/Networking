package com.networking.viewModel

import android.app.Application
import android.content.Context
import android.os.AsyncTask

import android.widget.Toast
import androidx.lifecycle.*
import com.networking.data.DataRepository

import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.VideoListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class MainActivityViewModel (application: Application) : AndroidViewModel(application) {
    val userRepository=DataRepository()
    var progressVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    val data : LiveData<Response<VideoListModel>> = liveData(Dispatchers.IO) {
        val retrievedData = userRepository.getUsers()
        emit(retrievedData)

    }






}