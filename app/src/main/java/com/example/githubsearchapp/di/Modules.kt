package com.example.githubsearchapp.di

import com.example.data.mapper.GithubUserMapper
import com.example.data.repository.GithubUserRepositoryImpl
import com.example.domain.repository.GithubUserRepository
import org.koin.dsl.module

val mainModule = module {
    single { GithubUserMapper() }
    factory<GithubUserRepository> { GithubUserRepositoryImpl(get(), get()) }
}