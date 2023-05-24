package com.app.github.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.github.R
import com.app.github.data.model.User
import com.bumptech.glide.Glide

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val loginId: TextView = view.findViewById(R.id.login_id)
    private val description: TextView = view.findViewById(R.id.description)
    private val image: ImageView = view.findViewById(R.id.profile_image)

    fun bind(item: User, onItemClick: (User) -> Unit){
        loginId.text = item.login
        description.text = item.subscriptions_url
        Glide.with(image.context)
            .load(item.avatar_url)
            .circleCrop()
            .into(image)
        view.setOnClickListener { onItemClick(item) }
    }
    override fun toString(): String {
        return super.toString() + " '" + description.text + "'"
    }
}