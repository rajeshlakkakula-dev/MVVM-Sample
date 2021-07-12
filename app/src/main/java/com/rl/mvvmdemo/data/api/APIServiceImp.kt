package com.rl.mvvmdemo.data.api

import com.rl.mvvmdemo.data.model.User
import com.androidnetworking.AndroidNetworking
import com.rx2androidnetworking.Rx2AndroidNetworking

import io.reactivex.Single

class APIServiceImp : ApiService{
    override fun getUsers(): Single<List<User>> {

        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(User::class.java)


    }
}