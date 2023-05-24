package com.app.github.main.view

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.github.R
import com.app.github.data.model.User
import com.app.github.databinding.ActivitySearchUserBinding
import com.app.github.databinding.LoadingStateConfigBinding
import com.app.github.main.adapter.UserRecyclerViewAdapter
import com.app.github.main.viewmodel.SearchUserViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchUserBinding
    private lateinit var loadingStateConfig: LoadingStateConfigBinding
    private val searchViewModel by viewModel<SearchUserViewModel>()
    private lateinit var userRecyclerViewAdapter: UserRecyclerViewAdapter

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)
        loadingStateConfig = binding.loadStateLayout

        initViewModel()
        initSearchView()
        initRecyclerView()
        showNoRecordsAvailableMessage(getString(R.string.no_records_available), loading = false, showList = false)
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.searchViewModel = searchViewModel

        binding.searchViewModel!!.user.observe(this) {
            lifecycleScope.launch {
                userRecyclerViewAdapter.submitData(it)
            }
        }
    }


    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    Toast.makeText(applicationContext, "Entre com nome", Toast.LENGTH_LONG).show()
                } else {
                    searchJob?.cancel()
                    searchJob = lifecycleScope.launch {
                        binding.searchViewModel!!.searchUser(text)
                    }
                    binding.searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

        })
    }

    private fun initRecyclerView() {
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            userRecyclerViewAdapter = UserRecyclerViewAdapter() { user: User ->
                onItemClickListener(user)
            }

            userRecyclerViewAdapter.addLoadStateListener {
                when (adapter?.itemCount) {
                    0 -> {
                        when (it.refresh) {
                            is LoadState.Loading -> {
                                showNoRecordsAvailableMessage(loading = true)
                            }
                            is LoadState.Error -> {
                                showNoRecordsAvailableMessage(getString(R.string.try_again))
                            }
                            is LoadState.NotLoading -> {
                                showNoRecordsAvailableMessage(getString(R.string.no_records_found));
                            }
                        }
                    }
                    else -> {
                        showNoRecordsAvailableMessage(showList = true);
                    }
                }
            }
            adapter = userRecyclerViewAdapter
        }
    }

    private fun showNoRecordsAvailableMessage(errorMessage : String = "", loading : Boolean = false, showList : Boolean = false) {
        loadingStateConfig.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        binding.list.visibility = if (showList) View.VISIBLE else View.GONE
        loadingStateConfig.errorMessage.text = errorMessage
        loadingStateConfig.errorMessage.visibility = if (errorMessage.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun onItemClickListener(item: User) {
        searchViewModel.selectedUser = item
        //findNavController(this).navigate(R.id.action_userListFragment_to_searchUserFragment)
    }
}