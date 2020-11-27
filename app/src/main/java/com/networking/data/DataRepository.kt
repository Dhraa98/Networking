package com.networking.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.RetrofitInterface
import com.networking.retrofit.VideoListModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataRepository {

    var dataValue: MutableLiveData<VideoListModel> = MutableLiveData()

    suspend fun getUsers() = RetrofitClass.getClient.getVideosApi("get", "", "")



}