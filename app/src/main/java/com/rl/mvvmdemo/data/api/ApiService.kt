package com.rl.mvvmdemo.data.api

import com.rl.mvvmdemo.data.model.User
import io.reactivex.Single

interface ApiService {

    //Why single is used?
    //Why list is used ?

    fun getUsers(): Single<List<User>>
}