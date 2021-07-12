package com.rl.mvvmdemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rl.mvvmdemo.data.model.User
import com.rl.mvvmdemo.data.repository.MainRepo
import com.rl.mvvmdemo.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepo: MainRepo) : ViewModel() {



    private val  users = MutableLiveData<Resource<List<User>>>()
    private val compositeDisposable = CompositeDisposable()


    init {
        fetchUsers()
    }

    private fun fetchUsers(){

        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepo.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                }, { throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun getUsers() : LiveData<Resource<List<User>>>{

        return users;
    }









}