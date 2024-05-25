package com.example.githubsearchapp.di

import com.example.data.db.GithubUserDao
import com.example.data.db.GithubUserDb
import com.example.data.mapper.GithubUserMapper
import com.example.data.repository.GithubUserRepositoryImpl
import com.example.domain.repository.GithubUserRepository
import com.example.githubsearchapp.ui.user.UserViewModel
import com.example.githubsearchapp.ui.user.adapter.GithubUserListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { GithubUserMapper() }
    factory<GithubUserRepository> { GithubUserRepositoryImpl(get(), get(), get()) }
}

val githubUserModule = module {
    single<GithubUserDao> {
        val database = get<GithubUserDb>()
        database.githubUserDetailDao
    }
    viewModel { UserViewModel(get(), get(),get()) }
    factory { GithubUserListAdapter() }
}
