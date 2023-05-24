package com.app.github.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.github.data.model.User
import com.app.github.data.repository.IUserRepository
import com.app.github.data.repository.UserRepository
import com.app.github.utils.Resource
import kotlinx.coroutines.Dispatchers


class UserViewModel(private val userRepository: IUserRepository) : ViewModel() {

    var selectedUser: User? = null
    private val listUsersData = MutableLiveData<List<User>>()
    private var userData = MutableLiveData<User>()

    private fun getUsers(): MutableLiveData<List<User>> = listUsersData

    init {
        getUsers()
    }


    fun getAllUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userRepository.getAllUsers()))
            listUsersData.postValue(userRepository.getAllUsers())
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Erro ocorrido!"))
        }
    }



}

