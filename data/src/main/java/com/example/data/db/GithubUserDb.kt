package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dto.GithubUserDetailDto

@Database(entities = [GithubUserDetailDto::class], version = 1, exportSchema = false)
abstract class GithubUserDb : RoomDatabase() {

    abstract val githubUserDetailDao: GithubUserDao

}