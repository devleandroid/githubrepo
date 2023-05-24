package com.app.github.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.github.R
import com.app.github.data.api.ApiClient
import com.app.github.data.model.ListUser
import com.app.github.data.repository.UserRepository
import com.app.github.databinding.ActivityMainBinding
import com.app.github.main.adapter.UserAdapter
import com.app.github.main.viewmodel.UserViewModel
import com.app.github.main.viewmodel.UserViewModelFactory
import com.app.github.utils.Status


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
        /*binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )*/
        adapter.onItemClick = { user ->
            val uri: Uri = Uri.parse(user.html_url) // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.userViewModel = userViewModel
    }

    private fun setupObservers() {
        binding.userViewModel!!.getAllUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retriveList(users) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }


    private fun retriveList(users: ListUser) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}