package com.dzulfikar.usersapp.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzulfikar.usersapp.R
import com.dzulfikar.usersapp.data.source.entities.Users
import com.dzulfikar.usersapp.databinding.ItemUsersListBinding
import com.dzulfikar.usersapp.ui.detailuser.DetailUserActivity

class HomeRecycleViewAdapter(private val context: Context) : RecyclerView.Adapter<HomeRecycleViewAdapter.ViewHolder>() {

    private val usersList = ArrayList<Users>()

    fun setData(users: List<Users>?){
        if(users == null) return
            this.usersList.clear()
            this.usersList.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUsersListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size

    inner class ViewHolder(private val binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users){
            with(binding){

                Glide.with(context)
                    .load(Uri.parse(users.profilePic))
                    .circleCrop()
                    .into(itemUserCircleImageView)

                itemUserName.text = context.resources?.getString(R.string.item_user_name, users.firstName, users.lastName)

                itemView.setOnClickListener {
                    val intent = Intent(context, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_USER_ID, users.uid)
                    context.startActivity(intent)
                }

            }
        }
    }



}