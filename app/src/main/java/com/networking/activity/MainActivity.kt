package com.networking.activity

import android.content.Context
import android.graphics.Movie
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.networking.R
import com.networking.adapter.DataAdapter
import com.networking.retrofit.RetrofitClass
import com.networking.retrofit.VideoListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var progress: ProgressBar
    private lateinit var manager: GridLayoutManager
    private var adapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControls()

    }

    private fun initControls() {
        rvdata = findViewById(R.id.rvVideos)
        progress = findViewById(R.id.progress)
        if (isNetworkConnected()) {
            getDataCall()
        } else {
            Toast.makeText(this, R.string.msg_connect_internet, Toast.LENGTH_LONG).show()
        }

    }

    private fun getDataCall() {
        progress.visibility = View.VISIBLE
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
                    progress.visibility = View.GONE
                    val movies: List<VideoListModel.DataVideoList> =
                        response.body()!!.dataVideoList!!

                    initDataList(movies)


                } else {
                    progress.visibility = View.GONE
                    Toast.makeText(
                        this@MainActivity,
                        response!!.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<VideoListModel>?, t: Throwable?) {
                progress.visibility = View.GONE
                Toast.makeText(this@MainActivity, t!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initDataList(videoList: List<VideoListModel.DataVideoList>) {
        adapter = DataAdapter(this, videoList)
        manager = GridLayoutManager(this, 2)
        rvdata.adapter = adapter
        rvdata.layoutManager = manager
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