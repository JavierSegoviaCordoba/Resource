package com.javiersc.app.di

import com.javiersc.app.GitHubViewModel
import com.javiersc.app.data.datasource.local.GitHubDatabase
import com.javiersc.app.data.datasource.local.GitHubDatabaseImpl
import com.javiersc.app.data.datasource.network.GitHubApi
import com.javiersc.app.data.datasource.network.GitHubApiImpl
import com.javiersc.app.data.datasource.network.service.GitHubServiceBuilder
import com.javiersc.app.data.repo.GitHubRepo
import com.javiersc.app.data.repo.GitHubRepoImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val modules = module {
    single { GitHubServiceBuilder.service }
    single<GitHubApi> { GitHubApiImpl(gitHubService = get()) }
    single<GitHubDatabase> { GitHubDatabaseImpl() }
    single<GitHubRepo> { GitHubRepoImpl(gitHubApi = get(), gitHubDatabase = get()) }
    single { GitHubViewModel(gitHubRepo = get()) }
}