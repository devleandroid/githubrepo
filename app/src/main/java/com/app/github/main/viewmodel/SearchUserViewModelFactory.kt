package com.app.github.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.github.data.repository.SearchRepository
import com.app.github.data.repository.UserRepository

class SearchUserViewModelFactory(private val repository: SearchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchUserViewModel(repository) as T
    }
}
