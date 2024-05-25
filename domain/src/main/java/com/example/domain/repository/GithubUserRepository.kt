package com.example.domain.repository

import com.example.domain.data.Resource
import com.example.domain.model.GithubUserDetailData
import com.example.domain.model.GithubUserSearchData

interface GithubUserRepository {
//
    suspend fun searchUser(username: String, page: Int, perPage: Int): Resource<GithubUserSearchData>

    suspend fun getUserDetail(username: String): Resource<GithubUserDetailData>
}