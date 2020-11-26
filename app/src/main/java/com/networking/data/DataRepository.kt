package com.networking.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.RetrofitInterface
import com.networking.retrofit.VideoListModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataRepository {

    var dataValue: MutableLiveData<VideoListModel> = MutableLiveData()


    fun getDataCall(progressVisibility: MutableLiveData<Boolean>): LiveData<VideoListModel> {


        val cryptoObservable: Observable<VideoListModel> =
            RetrofitClass.getClient.getVideosApi("get", "", "")
        cryptoObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                progressVisibility.value = false
                dataValue.value = it
            }
        progressVisibility.value = true

        return dataValue
    }

}