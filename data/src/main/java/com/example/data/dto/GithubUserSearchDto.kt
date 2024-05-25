package com.example.data.dto

import com.squareup.moshi.Json

data class GithubUserSearchDto(
    @field:Json(name = "total_count")
    val totalCount: Int,
    @field:Json(name = "items")
    val items: List<GithubUserDto>
)