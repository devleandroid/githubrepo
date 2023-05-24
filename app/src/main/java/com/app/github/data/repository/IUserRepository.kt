package com.app.github.data.repository

import com.app.github.data.model.ListUser

interface IUserRepository {
    suspend fun getAllUsers(): ListUser
}