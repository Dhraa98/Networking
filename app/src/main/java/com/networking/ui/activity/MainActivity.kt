package com.networking.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.networking.R
import com.networking.adapter.DataAdapter

import com.networking.databinding.ActivityMainBinding
import com.networking.retrofit.VideoListModel
import com.networking.ui.base.ViewModelFactory
import com.networking.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: GridLayoutManager
    private var adapter: DataAdapter? = null
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(this.application)
        ).get(MainActivityViewModel::class.java)

        binding.lifecycleOwner = this
        initControls()

    }

    private fun initControls() {

        if (isNetworkConnected()) {
            mainViewModel.getDataCall(this)
        } else {
            Toast.makeText(this, R.string.msg_connect_internet, Toast.LENGTH_LONG).show()
        }
        addObserver()

    }

    private fun addObserver() {
        mainViewModel.dataValue.observe(this, Observer {
            val movies: List<VideoListModel.DataVideoList> =
                it!!.dataVideoList!!


            initDataList(movies)
        })
        mainViewModel.progress.observe(this, Observer {
            if (it) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }

        })
    }


    /* private fun getDataCall() {
         binding.progress.visibility = View.VISIBLE
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
                     binding.progress.visibility = View.GONE
                     val movies: List<VideoListModel.DataVideoList> =
                         response.body()!!.dataVideoList!!

                     initDataList(movies)


                 } else {
                     binding.progress.visibility = View.GONE
                     Toast.makeText(
                         this@MainActivity,
                         response!!.body()!!.message,
                         Toast.LENGTH_SHORT
                     ).show()
                 }

             }

             override fun onFailure(call: Call<VideoListModel>?, t: Throwable?) {
                 binding.progress.visibility = View.GONE
                 Toast.makeText(this@MainActivity, t!!.message, Toast.LENGTH_SHORT).show()
             }
         })
     }*/

    private fun initDataList(videoList: List<VideoListModel.DataVideoList>) {
        adapter = DataAdapter(this, videoList)
        manager = GridLayoutManager(this, 2)
        binding.rvVideos.adapter = adapter
        binding.rvVideos.layoutManager = manager
    }

    private fun isNetworkConnected(): Boolean {

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork

        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}