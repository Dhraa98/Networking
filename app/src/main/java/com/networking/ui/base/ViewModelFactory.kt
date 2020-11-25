package com.networking.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.networking.viewModel.MainActivityViewModel

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(application = application) as T


            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}