package com.app.github.data.repository

import androidx.paging.PagingData
import com.app.github.data.api.ApiHelper
import com.app.github.data.api.ApiService
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiService: ApiService): IUserRepository {
    override suspend fun getAllUsers(): ListUser {
        return apiService.getUsers()
    }
}