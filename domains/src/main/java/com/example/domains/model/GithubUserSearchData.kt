package com.example.domain.model

data class GithubUserSearchData(
    val totalCount: Int,
    val items: List<GithubUserData>
)