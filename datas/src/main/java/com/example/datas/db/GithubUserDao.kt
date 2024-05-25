package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dto.GithubUserDetailDto

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDetail: GithubUserDetailDto): Long

    @Query("SELECT * FROM user_detail WHERE login IS :username")
    suspend fun getUserDetail(username: String): GithubUserDetailDto

}