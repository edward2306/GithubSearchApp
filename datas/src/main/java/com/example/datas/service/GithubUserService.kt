package com.example.data.service

import com.example.data.dto.GithubUserDetailDto
import com.example.data.dto.GithubUserSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserService {
    @GET("/search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubUserSearchDto

    @GET("/users/{user}")
    suspend fun getUserDetail(@Path("user") user: String): GithubUserDetailDto

}