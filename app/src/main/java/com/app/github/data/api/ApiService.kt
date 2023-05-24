package com.app.github.data.api

import androidx.paging.PagingData
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): ListUser


    @GET("users/torvalds")
    fun getReceivedEvent(userName: String): Flow<PagingData<User>>
}