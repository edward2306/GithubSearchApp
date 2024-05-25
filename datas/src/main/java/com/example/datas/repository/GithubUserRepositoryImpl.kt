package com.example.data.repository

import com.example.data.api.ApiClient
import com.example.data.db.GithubUserDb
import com.example.data.mapper.GithubUserMapper
import com.example.domain.data.Resource
import com.example.domain.model.GithubUserDetailData
import com.example.domain.model.GithubUserSearchData
import com.example.domain.repository.GithubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubUserRepositoryImpl(
    private val githubUserMapper: GithubUserMapper,
    private val apiClient: ApiClient,
    private val db: GithubUserDb
    )
    : GithubUserRepository {
    override suspend fun searchUser(
        username: String,
        page: Int,
        perPage: Int
    ): Resource<GithubUserSearchData> {
        return try {
            val response = apiClient.getGithubService().search(username, page, perPage)
            Resource.Success(data = githubUserMapper.mapGithubUserSearchDto(response))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getUserDetail(
        username: String
    ): Resource<GithubUserDetailData> {
        return try {
            val response = apiClient.getGithubService().getUserDetail(username)
            withContext(Dispatchers.IO) {
                db.githubUserDetailDao.insert(response)
            }
            Resource.Success(data = githubUserMapper.mapGithubUserDetailDto(response))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}