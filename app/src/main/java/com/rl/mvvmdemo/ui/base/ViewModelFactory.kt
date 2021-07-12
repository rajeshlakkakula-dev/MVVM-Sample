package com.rl.mvvmdemo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rl.mvvmdemo.data.api.ApiHelper
import com.rl.mvvmdemo.data.repository.MainRepo
import com.rl.mvvmdemo.ui.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepo(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }


}