package com.app.github.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllUsers() = apiService.getUsers()

    fun getReceivedEvent(userName: String) = apiService.getReceivedEvent(userName)
}