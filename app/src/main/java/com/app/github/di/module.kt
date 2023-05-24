package com.app.github.di

import com.app.github.data.api.ApiClient
import com.app.github.data.api.ApiService
import com.app.github.data.repository.ISearchRepository
import com.app.github.data.repository.IUserRepository
import com.app.github.data.repository.SearchRepository
import com.app.github.data.repository.UserRepository
import com.app.github.main.viewmodel.SearchUserViewModel
import com.app.github.main.viewmodel.SearchUserViewModelFactory
import com.app.github.main.viewmodel.UserViewModel
import com.app.github.main.viewmodel.UserViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module



val gitHubInfo = module {

    single { ApiClient.getGitHubService() }
    single { ApiClient }

    single { UserViewModelFactory(get()) }
    single { SearchUserViewModelFactory(get()) }

    single<IUserRepository> { UserRepository(get()) }
    single<ISearchRepository> { SearchRepository(get()) }

    viewModel { UserViewModel(get()) }
    viewModel { SearchUserViewModel(get()) }
}