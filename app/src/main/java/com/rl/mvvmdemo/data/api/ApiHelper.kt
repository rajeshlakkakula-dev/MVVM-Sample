package com.rl.mvvmdemo.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers();

}