package com.app.github.data.repository

import androidx.paging.PagingData
import com.app.github.data.api.ApiService
import com.app.github.data.model.User
import kotlinx.coroutines.flow.Flow

class SearchRepository(private val apiService: ApiService): ISearchRepository {
    override suspend fun getReceivedEvent(userName: String): Flow<PagingData<User>> {
        return apiService.getReceivedEvent(userName)
    }
}