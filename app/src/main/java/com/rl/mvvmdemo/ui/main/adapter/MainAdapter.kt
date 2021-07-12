package com.rl.mvvmdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rl.mvvmdemo.R
import com.rl.mvvmdemo.data.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val users: ArrayList<User> ) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(user: User){
        itemView.tv_userName.text = user.name
        itemView.tv_email.text = user.email

        Glide.with(itemView.iv_avatar.context)
            .load(user.avatar)
            .into(itemView.iv_avatar)


    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =

        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =

        holder.bind(users[position])


    override fun getItemCount(): Int =  users.size


    fun addData(list: List<User>){
        users.addAll(list)
    }


}