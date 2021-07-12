package com.rl.mvvmdemo.ui.view



import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rl.mvvmdemo.R
import com.rl.mvvmdemo.data.api.APIServiceImp
import com.rl.mvvmdemo.data.api.ApiHelper
import com.rl.mvvmdemo.data.model.User
import com.rl.mvvmdemo.ui.base.ViewModelFactory
import com.rl.mvvmdemo.ui.main.adapter.MainAdapter
import com.rl.mvvmdemo.ui.viewmodel.MainViewModel
import com.rl.mvvmdemo.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }


    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {

            when(it.status){
                Status.SUCCESS -> {

                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING -> {

                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()

                }

            }



        })
    }


    private  fun renderList(users: List<User>){

        adapter.addData(users)
        adapter.notifyDataSetChanged()

    }

    private fun setupUI() {
       recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }


    private fun setupViewModel() {

        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(APIServiceImp()))
        ).get(MainViewModel::class.java)

    }






}