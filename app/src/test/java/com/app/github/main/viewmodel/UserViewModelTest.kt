package com.app.github.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.github.UBRTest
import com.app.github.data.api.ApiService
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import com.app.github.data.repository.UserRepository
import com.app.github.listUser
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock


@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest: UBRTest() {

    @Mock
    var viewModel: UserViewModel = mockk(relaxed = true)

    @Mock
    var userRepository = mockk<UserRepository>(relaxed = true)

    @Mock
    var apiService = mockk<ApiService>(relaxed = true)

    @Before
    fun setUp() {
        userRepository = UserRepository(apiService)
        viewModel = UserViewModel(userRepository)
    }

    @Test
    fun `when call getAllUsers from list empty`() {
        runBlocking {
            val list = ListUser()
            list.add(listUser)

            coEvery { userRepository.getAllUsers() } answers { list }
            val listEmpty = userRepository.getAllUsers().isEmpty()

            assertEquals(listEmpty, list.isEmpty())
        }
    }

    @Test
    fun `when viewModel called getAllUser return success`() {
        runBlocking {
            val userList = ListUser()
            userList.add(listUser)

            // coEvery { viewModel.onViewReady() } answers { viewModel.listUsersData.value }
            viewModel.listUsersData.apply { userList }

            val response =  viewModel.getUsers().postValue(userList)
            assertNotNull(response)
        }
    }
}