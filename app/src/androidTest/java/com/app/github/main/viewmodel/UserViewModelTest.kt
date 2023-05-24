package com.app.github.main.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import com.app.github.data.repository.IUserRepository
import com.app.github.utils.Resource
import com.app.github.utils.Status
import com.google.ar.core.Config
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(AndroidJUnit4::class)
class UserViewModelTest {


    private val userServices = mock(IUserRepository::class.java)

    private lateinit var viewModel: UserViewModel


    @Before
    fun setup(){
        viewModel = UserViewModel(userServices)
    }

    @After
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun getAllUsers()  = runBlocking {
        `when`(userServices.getAllUsers()).thenReturn(emptyArray<ListUser>() as ListUser?)
        viewModel = UserViewModel(userServices)
        assertEquals(Status.LOADING, viewModel.selectedUser)
    }

    @Test
    fun getUser() = runBlocking {
        viewModel.getAllUsers()
        var data : LiveData<Resource<ListUser>> = viewModel.getAllUsers()
        assertEquals(1, data.value)
        assertEquals("mojombo", data.value!!.data)

    }
}