package com.app.github.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.github.R
import com.app.github.data.model.ListUser
import com.app.github.databinding.ActivityMainBinding
import com.app.github.main.adapter.UserAdapter
import com.app.github.main.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()
        initViewModel()
        setupObservers()
        searchActivity()
    }

    private fun searchActivity() {
        binding.searchButton.setOnClickListener {
            val intent = Intent(this, SearchUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(arrayListOf())
        adapter.onItemClick = { user ->

            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("user", user)

            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.userViewModel = userViewModel
        binding.userViewModel!!.onViewReady()
    }

    private fun setupObservers() {
        binding.userViewModel!!.listUsersData.observe(this, Observer {
            binding.recyclerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            retriveList(it as ListUser)
        })
    }


    private fun retriveList(users: ListUser) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}