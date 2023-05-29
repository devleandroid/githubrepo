package com.app.github.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import com.app.github.data.repository.IUserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val userRepository: IUserRepository) : ViewModel() {

    var selectedUser: User? = null

    private val _listUser = MutableLiveData<ListUser>()
    val listUsersData: LiveData<ListUser> = _listUser
    var userData = MutableLiveData<User>()
    private val loading = MutableLiveData<Boolean>()
    fun getUsers(): MutableLiveData<ListUser>  = _listUser

    fun  onViewReady() {
        getAllUsers()
    }


    private fun getAllUsers() {
        viewModelScope.launch {
            loading.value = true
            _listUser.postValue(userRepository.getAllUsers())

            loading.value = false
        }
    }
}

