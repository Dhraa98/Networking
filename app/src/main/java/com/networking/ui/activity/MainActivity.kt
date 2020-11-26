package com.networking.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.networking.R
import com.networking.adapter.DataAdapter
import com.networking.databinding.ActivityMainBinding
import com.networking.retrofit.VideoListModel
import com.networking.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: GridLayoutManager
    private var adapter: DataAdapter? = null

    val viewModel: MainActivityViewModel by viewModels()
    // private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //viewModel = ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
        /*  viewModel = ViewModelProvider(
              this,
              ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
          )[MainActivityViewModel::class.java]*/

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        initControls()

    }

    private fun initControls() {

        if (isNetworkConnected()) {
            viewModel.userData.observe(this, Observer {

                if (it.dataVideoList!!.size > 0) {
                    val movies: List<VideoListModel.DataVideoList> =
                        it!!.dataVideoList!!

                    initDataList(movies)

                } else {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }

            })
            // viewModel.getDataCall(this)
        } else {
            Toast.makeText(this, R.string.msg_connect_internet, Toast.LENGTH_LONG).show()
        }
        // addObserver()

    }

    /* private fun addObserver() {
         viewModel.dataValue.observe(this, Observer {
             val movies: List<VideoListModel.DataVideoList> =
                 it!!.dataVideoList!!


             initDataList(movies)
         })

     }
 */

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