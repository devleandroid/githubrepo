package com.app.github.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import com.app.github.databinding.ItemUserLayoutBinding
import com.bumptech.glide.Glide

class UserAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var onItemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: ListUser) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(private val binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.loginUser.text = user.login
            binding.userName.text = user.html_url
            Glide.with(binding.imageAvatar.context)
                .load(user.avatar_url)
                .circleCrop()
                .into(binding.imageAvatar)
        }

        init {
            binding.cardView.setOnClickListener {
                onItemClick?.invoke(users[adapterPosition])
            }
        }
    }
}