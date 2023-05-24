package com.app.github.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.app.github.R
import com.app.github.data.model.User

class UserRecyclerViewAdapter(
    private val onItemClick: (User) -> Unit
) : PagingDataAdapter<User, ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onItemClick)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<User>() {

            override fun areItemsTheSame(oldUser: User,
                                         newUser: User) = oldUser.login == newUser.login

            override fun areContentsTheSame(oldUser: User,
                                            newUser: User) = oldUser == newUser
        }
    }
}