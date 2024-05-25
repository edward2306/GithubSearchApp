package com.example.data.api

import android.app.Application
import androidx.room.Room
import com.example.data.db.GithubUserDao
import com.example.data.db.GithubUserDb
import com.example.data.di.Interceptor
import com.example.data.service.GithubUserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.github.com"

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(Interceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
        .build()

    fun getGithubService(): GithubUserService = retrofit.create(
        GithubUserService::class.java)

    fun getUserDetailDao(database: GithubUserDb): GithubUserDao {
        return database.githubUserDetailDao
    }

    fun getDatabase(application: Application): GithubUserDb {
        return Room.databaseBuilder(application, GithubUserDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}