package com.app.github.main.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.github.data.model.User
import com.app.github.data.repository.ISearchRepository
import com.app.github.data.repository.SearchRepository
import com.app.github.data.repository.UserRepository

class SearchUserViewModel(private val searchRepository: ISearchRepository) : ViewModel() {
    var selectedUser: User? = null

    private val userLiveData = MediatorLiveData<PagingData<User>>()
    val user = userLiveData
    var userName: String? = null


    suspend fun searchUser(queryString: String) {
        if (queryString == userName) {
            return
        }
        userName = queryString
        userLiveData.addSource(
            searchRepository.getReceivedEvent(queryString).cachedIn(viewModelScope).asLiveData()
        ) {
            userLiveData.value = it
        }
    }


    private fun getReceivedEvent(userName: String) {
        this.userName = userName
    }
}