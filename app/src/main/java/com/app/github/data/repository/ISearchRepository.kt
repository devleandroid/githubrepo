package com.app.github.data.repository

import androidx.paging.PagingData
import com.app.github.data.model.User
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    suspend fun getReceivedEvent(userName: String): Flow<PagingData<User>>
}