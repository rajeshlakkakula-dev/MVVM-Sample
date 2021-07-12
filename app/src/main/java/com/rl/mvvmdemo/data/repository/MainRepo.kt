package com.rl.mvvmdemo.data.repository

import com.rl.mvvmdemo.data.api.ApiHelper
import com.rl.mvvmdemo.data.model.User
import io.reactivex.Single

class MainRepo(private val apiHelper: ApiHelper) {

    fun getUser(): Single<List<User>>{


        return apiHelper.getUsers()
    }



}